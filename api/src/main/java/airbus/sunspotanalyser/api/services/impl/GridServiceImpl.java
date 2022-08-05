package airbus.sunspotanalyser.api.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import airbus.sunspotanalyser.api.Properties;
import airbus.sunspotanalyser.api.daos.GridDao;
import airbus.sunspotanalyser.api.daos.ScoresDao;
import airbus.sunspotanalyser.api.entities.Grid;
import airbus.sunspotanalyser.api.entities.Scores;
import airbus.sunspotanalyser.api.exceptions.DuplicateException;
import airbus.sunspotanalyser.api.exceptions.NotFoundException;
import airbus.sunspotanalyser.api.services.GridService;

@Service
public class GridServiceImpl implements GridService {

	@Autowired
	private Properties properties;

	@Resource
	private GridDao gridDao;

	@Resource
	private ScoresDao scoresDao;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Grid> list(Integer page, Integer size, String sortField, Boolean sortOrder, Optional<String> searchText) {
		Page<Grid> grids;
		final Pageable pageable = PageRequest.of(page, size, sortOrder ? Direction.ASC : Direction.DESC, sortField);
		
		grids = gridDao.findAll(pageable);

		return grids;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grid> list() {
		return gridDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Grid get(Long id) {
		return gridDao.findById(id).orElseThrow(() -> new NotFoundException("grid not found"));
	}

	//@Override
	//@Transactional(readOnly = true)
	//public Grid get() {
	//	Grid grid = gridDao.findById(authService.getGridId()).orElseThrow(() -> new NotFoundException("grid not found"));
	//
	//	return grid;
	//}

	@Override
	@Transactional
	public Grid create(Grid grid) {
	
		validate(grid);
		
		Grid finalGrid = gridDao.save(grid);
		
		//Calculate score		
		String[] values = finalGrid.getGridvalues().split(",");
        int size = finalGrid.getSize();
        int [][] array = new int [size][size];
		
        for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				array[i][j] = Integer.parseInt(values[i*size + j].strip());
			}
        }
		
		String scores = "[";
		
        for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				int score = array[i][j];
				
				int[][] directions = new int[][]{{-1,-1}, {-1,0}, {-1,1},  {0,1}, {1,1},  {1,0},  {1,-1},  {0, -1}};
				
				for (int[] direction : directions) {
					int cx = i + direction[0];
					int cy = j + direction[1];
					if(cy >=0 && cy < array.length)
						if(cx >= 0 && cx < array[cy].length)
							score += array[cy][cx];
				}
				
				scores += String.format("{'x': %d, 'y': %d, 'score': %d},", i, j, score);
			}
        }
		
		scores += "]";
		
		//Create scores object		
		Scores obj = new Scores();
		obj.setIdGrid(finalGrid.getId());
		obj.setScores(scores);
		
		scoresDao.save(obj);

		return finalGrid;
	}

	@Override
	@Transactional
	public Grid update(Grid grid) {

		validate(grid);
		
		// save (grid)
		Grid storedGrid = gridDao.findById(grid.getId()).orElseThrow(() -> new NotFoundException("Grid not found"));

		storedGrid.setSize(grid.getSize());
		storedGrid.setGridvalues(grid.getGridvalues());

		return gridDao.save(storedGrid);
	}	

	@Override
	@Transactional
	public void delete(Long id) {

		Grid storedGrid = gridDao.findById(id).orElseThrow(() -> new NotFoundException("grid not found"));

		gridDao.delete(storedGrid);
	}

	/**
	 * Validates a grid.
	 * @param grid - the grid
	 */
	private void validate(Grid grid) {
	}

}

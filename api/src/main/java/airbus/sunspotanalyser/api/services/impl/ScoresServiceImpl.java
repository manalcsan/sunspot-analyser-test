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
import airbus.sunspotanalyser.api.daos.ScoresDao;
import airbus.sunspotanalyser.api.entities.Scores;
import airbus.sunspotanalyser.api.exceptions.DuplicateException;
import airbus.sunspotanalyser.api.exceptions.NotFoundException;
import airbus.sunspotanalyser.api.services.ScoresService;

@Service
public class ScoresServiceImpl implements ScoresService {

	@Autowired
	private Properties properties;

	@Resource
	private ScoresDao scoresDao;

	@Override
	@Transactional(readOnly = true)
	public Page<Scores> list(Integer page, Integer size, String sortField, Boolean sortOrder, Optional<String> searchText) {
		Page<Scores> scoress;
		final Pageable pageable = PageRequest.of(page, size, sortOrder ? Direction.ASC : Direction.DESC, sortField);
		
		scoress = scoresDao.findAll(pageable);

		return scoress;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Scores> list() {
		return scoresDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Scores> get(Long id) {
		return scoresDao.findAllByGridId(id);
		//return scoresDao.findById(id).orElseThrow(() -> new NotFoundException("scores not found"));
	}

	//@Override
	//@Transactional(readOnly = true)
	//public Scores get() {
	//	Scores scores = scoresDao.findById(authService.getScoresId()).orElseThrow(() -> new NotFoundException("scores not found"));
	//
	//	return scores;
	//}

	@Override
	@Transactional
	public Scores create(Scores scores) {
	
		validate(scores);

		return scoresDao.save(scores);
	}

	@Override
	@Transactional
	public Scores update(Scores scores) {

		validate(scores);
		
		// save (scores)
		Scores storedScores = scoresDao.findById(scores.getId()).orElseThrow(() -> new NotFoundException("Scores not found"));

		storedScores.setIdGrid(scores.getIdGrid());
		storedScores.setScores(scores.getScores());

		return scoresDao.save(storedScores);
	}	

	@Override
	@Transactional
	public void delete(Long id) {

		Scores storedScores = scoresDao.findById(id).orElseThrow(() -> new NotFoundException("scores not found"));

		scoresDao.delete(storedScores);
	}

	/**
	 * Validates a scores.
	 * @param scores - the scores
	 */
	private void validate(Scores scores) {
	}

}

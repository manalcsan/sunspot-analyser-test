package airbus.sunspotanalyser.api.controllers.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * Custom validator for multipart (max size, name length, regex).
 * @author Miguel Angel Ruiz Martinez
 */
public class MultipartFileFormatValidator implements ConstraintValidator<MultipartFileFormat, MultipartFile> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MultipartFileFormatValidator.class);

	private long maxSize;
	private int maxNameLength;
	private String regexp;

	public void initialize(MultipartFileFormat constraintAnnotation) {
		this.maxSize = constraintAnnotation.maxSize();
		this.maxNameLength = constraintAnnotation.maxNameLength();
		this.regexp = constraintAnnotation.regexp();
	}

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		boolean isValid = true;
		if (value != null && value.getOriginalFilename() != null) {
			String fileName = value.getOriginalFilename().trim();
			if (maxSize >= 0 && value.getSize() > maxSize) {
				LOGGER.debug("multipart file size is higher than {} bytes - name: {}", maxSize, fileName);
				isValid = false;
			}
			if (maxNameLength >= 0 && fileName.length() > maxNameLength) {
				LOGGER.debug("multipart file name is logner that {} - name: {}", maxNameLength, fileName);
				isValid = false;
			}
			if (!StringUtils.isBlank(regexp) && !fileName.matches(regexp)) {
				LOGGER.debug("multipart file name has wrong pattern - name: {}, pattern: {}", fileName, regexp);
				isValid = false;
			}
		}

		return isValid;
	}

}

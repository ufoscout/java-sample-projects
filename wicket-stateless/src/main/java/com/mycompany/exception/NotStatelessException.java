package com.mycompany.exception;

import com.mycompany.StatelessWebPage;

/**
 * An exception thrown when trying to render a {@link StatelessWebPage}
 * that declare not stateless components
 * @author ufo
 *
 */
public class NotStatelessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotStatelessException(String message) {
		super(message);
	}
}

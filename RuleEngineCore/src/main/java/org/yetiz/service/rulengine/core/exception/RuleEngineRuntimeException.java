package org.yetiz.service.rulengine.core.exception;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.yetiz.service.rulengine.core.Loggable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by yeti on 2014/12/1.
 */
public class RuleEngineRuntimeException extends RuntimeException {
	protected static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static final long serialVersionUID = -6731068176993566859L;
	private boolean throwed = false;
	private static Gson jsonConverter = new Gson();
	protected String code = "RE0000";

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for
	 *                later retrieval by the {@link #getMessage()} method.
	 */
	private RuleEngineRuntimeException(String message) {
		super(message);
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for
	 *                later retrieval by the {@link #getMessage()} method.
	 */
	public RuleEngineRuntimeException(String message, String code) {
		super(message);
		this.code = code;
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *
	 * @param object the detail object. The detail message is saved for
	 *               later retrieval by the {@link #getMessage()} method.
	 */
	public RuleEngineRuntimeException(String message, Object object, String code) {
		super(message + toSerializedString(object));
		this.code = code;
	}

	public static String toSerializedString(Object object) {
		if (object instanceof Loggable) {
			Loggable logObj = ((Loggable) object);
			logObj.BASE.getID();
			logObj.BASE.getName();
			try {
				String rtn = "Object: " + jsonConverter.toJson(logObj);
				return rtn;
			} catch (Exception e) {
			}
		}
		if (object instanceof Serializable)
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				oos.close();
				String rtn = " Object [Bae64]:" + new String(Base64.encodeBase64(baos.toByteArray()));
				baos.close();
				return rtn;
			} catch (IOException e) {
				throw new RuleEngineRuntimeException(e.toString());
			}
		return "";
	}

	public String getCode() {
		return code;
	}

	/**
	 * Creates a localized description of this throwable.
	 * Subclasses may override this method in order to produce a
	 * locale-specific message.  For subclasses that do not override this
	 * method, the default implementation returns the same result as
	 * {@code getMessage()}.
	 *
	 * @return The localized description of this throwable.
	 * @since JDK1.1
	 */
	@Override
	public String getLocalizedMessage() {
		StringBuilder rtn = new StringBuilder();
		rtn.append(LINE_SEPARATOR + "---START EXCEPTION INFO---" + LINE_SEPARATOR);
		rtn.append("Name:       " + getClass().getCanonicalName() + LINE_SEPARATOR);
		rtn.append("Code:       " + getCode() + LINE_SEPARATOR);
		rtn.append("Message:    " + getMessage() + LINE_SEPARATOR);
		rtn.append("Stack:      " + LINE_SEPARATOR);
		for (int i = 0; i < getStackTrace().length; i++) {
			rtn.append("\t" + getStackTrace()[i] + LINE_SEPARATOR);
		}
		rtn.append("---END EXCEPTION INFO---" + LINE_SEPARATOR);
		return rtn.toString();
	}

	/**
	 * Returns a short description of this throwable.
	 * The result is the concatenation of:
	 * <ul>
	 * <li> the {@linkplain Class#getName() name} of the class of this object
	 * <li> ": " (a colon and a space)
	 * <li> the result of invoking this object's {@link #getLocalizedMessage}
	 * method
	 * </ul>
	 * If {@code getLocalizedMessage} returns {@code null}, then just
	 * the class name is returned.
	 *
	 * @return a string representation of this throwable.
	 */
	@Override
	public String toString() {
		if (!throwed) {
			throwed = true;
			return super.toString();
		}
		return "";
	}
}

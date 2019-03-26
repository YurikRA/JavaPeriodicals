package ua.ruban.exception;

/**
 * Holder for messages of exceptions.
 * 
 * @author Y.Ruban
 *
 */
public class Messages {

	private Messages() {

	}

	//DB exceptions
	public static final String ERR_CANNOT_OBTAIN_EDITION = "Cannot obtain edition";

	public static final String ERR_CANNOT_OBTAIN_EDITION_BY_SEARCH = "Cannot obtain edition by search";

	public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";

	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";

	public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its number";

	public static final String ERR_CANNOT_OBTAIN_ALL_USERS = "Cannot obtain a users";

	public static final String ERR_CANNOT_INSERT_USER = "Cannot insert a user";

	public static final String ERR_CANNOT_DELETE_USER_BY_ID = "Cannot delete a user";

	public static final String ERR_CANNOT_OBTAIN_CATEGORIES = "Cannot obtain categories";

	public static final String ERR_CANNOT_INSERT_EDITION = "Cannot insert edition";

	public static final String ERR_CANNOT_DELETE_EDITION_BY_ID = "Cannot delete edition";

	public static final String ERR_CANNOT_OBTAIN_EDITIONS = "Cannot obtain editions";

	public static final String ERR_CANNOT_INSERT_SUBSCRIPTION = "Cannot insert subscription";

	public static final String ERR_CANNOT_OBTAIN_SUBSCRIPTION = "Cannot obtain subscription";

	public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";

	public static final String ERR_CANNOT_UPDATE_EDITION = "Cannot update a user";

	public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";

	public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

	public static final String ERR_CANNOT_INSERT_CATEGORY = "Cannot insert category";

	public static final String ERR_CANNOT_OBTAIN_ALL_SUBS = "Cannot obtain subs";

}
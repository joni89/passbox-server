package com.jonatancm.passbox.config.hibernate;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;

import java.sql.Types;

public class SQLiteDialect extends Dialect {

	public SQLiteDialect() {
		registerColumnType(Types.BIT, "integer");
		registerColumnType(Types.TINYINT, "tinyint");
		registerColumnType(Types.SMALLINT, "smallint");
		registerColumnType(Types.INTEGER, "integer");
		// other data types
	}

	@Override
	public IdentityColumnSupport getIdentityColumnSupport() {
		return new SQLiteIdentityColumnSupport();
	}

}

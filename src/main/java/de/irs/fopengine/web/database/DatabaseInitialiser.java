package de.irs.fopengine.web.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInitialiser {



    public static final String DROP_USER = "DROP table user";
    public static final String DROP_FONT = "DROP table font";
    public static final String DROP_PROJECT = "DROP table project";
    public static final String CREATE_USER = "CREATE TABLE IF NOT EXISTS `user` " +
            "(`id` INT NOT NULL AUTO_INCREMENT," +
            "`username` VARCHAR(45) NULL, " +
            "`password` VARCHAR(45) NOT NULL, " +
            "PRIMARY KEY (`id`))";

    public static final String CREATE_PROJECT = "CREATE TABLE IF NOT EXISTS `project` " +
            "(`id` INT NOT NULL AUTO_INCREMENT,  " +
            "`name` VARCHAR(45) NULL," +
            "`url` VARCHAR(45) NOT NULL," +
            "`description` VARCHAR(255) NOT NULL," +
            "PRIMARY KEY (`id`))";

    public static final String CREATE_FONT = "CREATE TABLE IF NOT EXISTS `font` " +
            "(`id` INT NOT NULL AUTO_INCREMENT," +
            "`font_name` VARCHAR(45) NULL,  " +
            "`project_id` INT NOT NULL," +
            "PRIMARY KEY (`id`))";

    public static final String INSERT_USER_1 = "INSERT INTO user (username, password) VALUES ('a', 'a')";
    public static final String INSERT_USER_2 = "INSERT INTO user (username, password) VALUES ('petr@aaa.cz', 'aaa')";
    public static final String INSERT_PROJECT_1 = "INSERT INTO project (name, url, description) VALUES ('project1', 'url1', 'description1')";
    public static final String INSERT_PROJECT_2 = "INSERT INTO project (name, url, description) VALUES ('project2',  'url2', 'description2')";

    public static final String INSERT_FONT_1 = "INSERT INTO font (font_name, project_id) VALUES ('font1', %1% )";
    public static final String INSERT_FONT_2 = "INSERT INTO font (font_name, project_id) VALUES ('font2', %1% )";
    public static final String INSERT_FONT_3 = "INSERT INTO font (font_name, project_id) VALUES ('font3', %1% )";
    public static final String INSERT_FONT_4 = "INSERT INTO font (font_name, project_id) VALUES ('font4', %1% )";


    public void dropTables() throws SQLException {
//        database.update(DROP_USER, new ArrayList<>());
//        database.update(DROP_FONT, new ArrayList<>());
        Database.getInstance().update(DROP_PROJECT, new ArrayList<>());
    }
    public void createTables() throws SQLException {
        Database.getInstance().update(CREATE_USER, new ArrayList<>());
        Database.getInstance().update(CREATE_FONT, new ArrayList<>());
        Database.getInstance().update(CREATE_PROJECT, new ArrayList<>());
    }

    public void clearTables() throws SQLException {
        Database.getInstance().update("delete from user", new ArrayList<>());
        Database.getInstance().update("delete from font", new ArrayList<>());
        Database.getInstance().update("delete from project", new ArrayList<>());
    }

    public void insertData() throws SQLException {
        Database.getInstance().insert(INSERT_USER_1);
        Database.getInstance().insert(INSERT_USER_2);
        int id = Database.getInstance().insert(INSERT_PROJECT_1);
        Database.getInstance().insert(INSERT_FONT_1.replace("%1%", ""+id));
        Database.getInstance().insert(INSERT_FONT_2.replace("%1%", ""+id));
        id = Database.getInstance().insert(INSERT_PROJECT_2);
        Database.getInstance().insert(INSERT_FONT_3.replace("%1%", ""+id));
        Database.getInstance().insert(INSERT_FONT_4.replace("%1%", ""+id));
    }

    public static void main(String[] args) throws SQLException {
        DatabaseInitialiser initializer = new DatabaseInitialiser();
//        initializer.dropTables();
//        initializer.createTables();
        initializer.clearTables();
        initializer.insertData();

    }





}


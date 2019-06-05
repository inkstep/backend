package store;

import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;

public class InkstepDatabaseSchema {

  private static final DbSpec SPEC = new DbSpec();
  private static final DbSchema SCHEMA = SPEC.addDefaultSchema();

  // Users
  public static final DbTable USERS = SCHEMA.addTable("users");
  public static final DbColumn USER_ID = USERS.addColumn("ID", "int", 11); // PK
  public static final DbColumn USER_NAME = USERS.addColumn("Name", "varchar", 45);
  public static final DbColumn USER_EMAIL = USERS.addColumn("Email", "varchar", 45);
  public static final DbColumn USER_PHONE = USERS.addColumn("Phone", "varchar", 45);
  public static final DbColumn USER_PASSPHRASE = USERS.addColumn("Passphrase", "varchar", 45);
  public static final DbColumn[] USER_COLUMNS =
    new DbColumn[] {USER_ID, USER_NAME, USER_EMAIL, USER_PHONE, USER_PASSPHRASE};

  // Artists
  public static final DbTable ARTISTS = SCHEMA.addTable("artists");
  public static final DbColumn ARTIST_ID = ARTISTS.addColumn("ID", "int", 11); // PK
  public static final DbColumn ARTIST_STUDIO_ID = ARTISTS.addColumn("StudioID", "int", 11);
  public static final DbColumn ARTIST_NAME = ARTISTS.addColumn("Name", "varchar", 127);
  public static final DbColumn ARTIST_EMAIL = ARTISTS.addColumn("Email", "varchar", 45);
  public static final DbColumn[] ARTIST_COLUMNS =
    new DbColumn[] {ARTIST_ID, ARTIST_STUDIO_ID, ARTIST_NAME, ARTIST_EMAIL};

  // Studios
  public static final DbTable STUDIOS = SCHEMA.addTable("studios");
  public static final DbColumn STUDIO_ID = STUDIOS.addColumn("ID", "int", 11);
  public static final DbColumn STUDIO_NAME = STUDIOS.addColumn("Name", "varchar", 45);
  public static final DbColumn[] STUDIO_COLUMNS = new DbColumn[] {STUDIO_ID, STUDIO_NAME};

  // Journeys
  public static final DbTable JOURNEYS = SCHEMA.addTable("journeys");
  public static final DbColumn JNY_ID = JOURNEYS.addColumn("ID", "int", 11); // PK
  public static final DbColumn JNY_USER_ID = JOURNEYS.addColumn("UserID", "int", 11); // FK
  public static final DbColumn JNY_ARTIST_ID = JOURNEYS.addColumn("ArtistID", "int", 11); // FK
  public static final DbColumn JNY_DESCRIPTION = JOURNEYS.addColumn("Description", "varchar", 45);
  public static final DbColumn JNY_SIZE = JOURNEYS.addColumn("Size", "varchar", 45);
  public static final DbColumn JNY_POSITION = JOURNEYS.addColumn("Position", "varchar", 45);
  public static final DbColumn JNY_AVAIL = JOURNEYS.addColumn("Availability", "varchar", 45);
  public static final DbColumn JNY_DEPOSIT = JOURNEYS.addColumn("Deposit", "varchar", 45);
  public static final DbColumn JNY_NO_REF_IMAGES = JOURNEYS.addColumn("NoRefImages", "int", 11);
  public static final DbColumn[] JOURNEY_COLUMNS =
    new DbColumn[] {JNY_ID, JNY_USER_ID, JNY_ARTIST_ID, JNY_DESCRIPTION, JNY_SIZE, JNY_POSITION,
      JNY_AVAIL, JNY_DEPOSIT, JNY_NO_REF_IMAGES};

  // Journey Images
  public static final DbTable JOURNEY_IMAGES = SCHEMA.addTable("journey_images");
  public static final DbColumn JNY_IMAGE_ID = JOURNEY_IMAGES.addColumn("ID", "int", 11); // PK
  public static final DbColumn JNY_IMAGE_JNY_ID = JOURNEY_IMAGES.addColumn("JourneyID", "int", 11);
  public static final DbColumn JNY_IMAGE_DATA = JOURNEY_IMAGES.addColumn("ImageData", "mediumtext", 65536);
  public static final DbColumn[] JOURNEY_IMAGE_COLUMNS =
    new DbColumn[] {JNY_IMAGE_ID, JNY_IMAGE_JNY_ID};
}

package com.and.ibrahim.teleprompter.data;

import android.net.Uri;
import android.provider.BaseColumns;

/*Created by ibrahim on 26/05/18.
 */
public class Contract implements BaseColumns {


    //extra strings saved in bundle
    public static final String EXTRA_STATE_FIRST_OPEN = "state_first_open";
    public static final String EXTRA_POSITION = "extra_position";
    public static final String EXTRA_STATE_INDEX = "state_index";
    public static final String EXTRA_ROTATION = "state_rotation";
    public static final String EXTRA_NO_ROTATION = "state_no_rotation";
    public static final String EXTRA_IS_TABLET = "extra_tablet";
    public static final String EXTRA_TELEPROMPTER_POSITION = "teleprompter_position";
    public static final String EXTRA_TELEPROMPTER_FRAGMENT = "teleprompter_fragment";


    //content provider
    static final String PATH = "teleprompter";

    static final String AUTHORITY = "com.and.ibrahim.teleprompter";
    private static final String SCHEMA = "content://";
    private static final Uri BASE_CONTENT_URI = Uri.parse(SCHEMA + AUTHORITY);

    public static final class BakeEntry implements BaseColumns {
        public static final Uri PATH_TELEPROMPTER_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();
        //for table of teleprompter
        public static final String TABLE_TELEPROMPTER = "teleprompter_bake";
        public static final String COL_TEXT = "text";
        public static final String DROP_TELEPROMPTER_TELEPROMPTER = "DROP TABLE IF EXISTS " + TABLE_TELEPROMPTER;
        public static final String CREATE_TABLE_TELEPROMPTER = "create table " + TABLE_TELEPROMPTER + "(" +
                _ID + " integer primary key autoincrement not null," +
                COL_TEXT + " text not null)";

        //database name
        static final String DB_NAME = "teleprompter_app.db";

    }


}
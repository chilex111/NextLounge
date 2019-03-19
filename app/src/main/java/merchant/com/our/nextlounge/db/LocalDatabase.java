package merchant.com.our.nextlounge.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import merchant.com.our.nextlounge.model.OrderModel;
import merchant.com.our.nextlounge.model.PriceModel;


public class LocalDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="The_Next";
    private static final int DATABASE_VERSION = 4;

    private static final String SAVE_ORDER = "save_order";
    private static final String FOOD_NAME = "name";
    private static final String FOOD_DESCRIPTION = "food_detail";
    private static final String NUMBER_OF_ORDER = "number";
    private static final String PRICE = "price";
    private static final String FOOD_IMAGE = "image";

    private static final String MENU_ORDERS = "CREATE TABLE "+ SAVE_ORDER +
            " ("+ FOOD_NAME + " TEXT, "+ FOOD_DESCRIPTION + " TEXT, "+ NUMBER_OF_ORDER +" TEXT, "
            + FOOD_IMAGE +" TEXT, " + PRICE +" TEXT );";


    private Context context;
    private String TAG ="Local_Database";

    public LocalDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(MENU_ORDERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ SAVE_ORDER);
        onCreate(sqLiteDatabase);
    }

    public void clearDB(){
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ SAVE_ORDER);
        onCreate(sqLiteDatabase);
    }
    public void insertOrder(List<OrderModel> modelList) {
        SQLiteDatabase db = null;
        ContentValues contentValues;
        if (modelList != null && (!modelList.isEmpty())){
            db = this.getWritableDatabase();
            for (OrderModel cardModel : modelList){
                if (!orderExists(cardModel.getFoodName(), db)){
                    contentValues = new ContentValues();
                    contentValues.put(FOOD_DESCRIPTION,cardModel.getFood_description());
                    contentValues.put(NUMBER_OF_ORDER,cardModel.getQuantity());
                    contentValues.put(PRICE,cardModel.getAmount());
                    contentValues.put(FOOD_IMAGE,cardModel.getImage());
                    db.insert(SAVE_ORDER, null, contentValues);
                }else {
                     db = getReadableDatabase();
                    long count = DatabaseUtils.queryNumEntries(db, SAVE_ORDER, NUMBER_OF_ORDER);
                    long total = count + Long.parseLong(cardModel.getQuantity());
                    contentValues = new ContentValues();
                    contentValues.put(NUMBER_OF_ORDER,String.valueOf(total));
                }
            }
        }
        if (db != null)
            db.close();
        Log.i("Card", "Card inserted");
    }
    private String[] orders = new String[]{PRICE, FOOD_DESCRIPTION,FOOD_NAME,FOOD_IMAGE,NUMBER_OF_ORDER};

    public List<OrderModel> getAllOrder() {
        List<OrderModel> modelArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(SAVE_ORDER, orders, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                OrderModel orderModel = new OrderModel();
                orderModel.setAmount(cursor.getString(0));
                orderModel.setFood_description(cursor.getString(1));
                orderModel.setFoodName(cursor.getString(2));
                orderModel.setImage(cursor.getString(3));
                orderModel.setQuantity(cursor.getString(4));

                modelArrayList.add(orderModel);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
            return modelArrayList;
        } else if (cursor != null) {
            cursor.close();
            db.close();
            return modelArrayList;
        } else {
            throw new AssertionError();
        }
    }

    private String[] allPrice = new String[]{PRICE};

    public List<PriceModel> getAllPrice() {
        List<PriceModel> modelArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(SAVE_ORDER, this.allPrice, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PriceModel orderModel = new PriceModel();
                orderModel.setAmount(cursor.getInt(0));

                modelArrayList.add(orderModel);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();

            return modelArrayList;
        } else if (cursor != null) {
            cursor.close();
            db.close();
            return modelArrayList;
        } else {
            throw new AssertionError();
        }
    }

    private boolean orderExists(String cardNo, SQLiteDatabase db) {
        boolean status = false;
        Cursor cr = db.query(SAVE_ORDER, new String[]{FOOD_DESCRIPTION},
                FOOD_DESCRIPTION + " = ?", new String[]{cardNo}, null, null, null, null);
        if (cr != null && (cr.getCount() > 0)){
            status = true;
        }
        assert  cr != null;
        cr.close();
        return status;
    }

    public void clearCard(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SAVE_ORDER, null, null);
    }

    public int orderCount() {
        SQLiteDatabase db = getReadableDatabase();

        long count = DatabaseUtils.queryNumEntries(db, SAVE_ORDER);
        db.close();
        Log.i(this.TAG, "cart count: " + count);
        return (int) count;
    }

}

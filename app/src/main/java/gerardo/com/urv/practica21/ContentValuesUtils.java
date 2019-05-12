package gerardo.com.urv.practica21;

import android.content.ContentValues;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;
import gerardo.com.urv.practica21.Models.Floor;
import gerardo.com.urv.practica21.Models.Location;
import gerardo.com.urv.practica21.Models.Parking;
import gerardo.com.urv.practica21.Models.Slot;

public class ContentValuesUtils {
    public static ContentValues modelToContentValuesP (Parking parking){
        ContentValues contentValues = new ContentValues();
        try{
            contentValues.put(ModelContracts.ParkingContract.ID, parking.getId());
            contentValues.put(ModelContracts.ParkingContract.NAME, parking.getName());
            contentValues.put(ModelContracts.ParkingContract.COMPANY_NUMBER, parking.getCompany_number());
            contentValues.put(ModelContracts.ParkingContract.LOCATION_ID, parking.getLocation().getId());
        }catch (Exception e){
            System.out.println("It's an error");
        }
        return contentValues;
    }

    public static ContentValues modelToContentValuesL (Location location, String nom) {
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(ModelContracts.LocationContract.ID, location.getId());
            contentValues.put(ModelContracts.LocationContract.NAME, nom);
            contentValues.put(ModelContracts.LocationContract.LATITUDE, location.getLatitude());
            contentValues.put(ModelContracts.LocationContract.LONGITUDE, location.getLongitude());
            contentValues.put(ModelContracts.LocationContract.POSTAL_CODE, location.getPostal_code());
            contentValues.put(ModelContracts.LocationContract.STATE_PROVINCE, location.getState_province());
            contentValues.put(ModelContracts.LocationContract.STREET_ADDRESS, location.getStreet_address());
        }catch (Exception e){
            System.out.println("It's an error");
        }
        return contentValues;
    }

    public static ContentValues modelToContentValuesF (Floor floor){
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(ModelContracts.FloorContract.ID, floor.getId());
            contentValues.put(ModelContracts.FloorContract.NAME, floor.getName());
            contentValues.put(ModelContracts.FloorContract.COMPANY_NUMBER, floor.getCompany_number());
        }catch (Exception e){
            System.out.println("It's an errror");
        }
        return contentValues;
    }

    public static ContentValues modelToContentValuesS (Slot slot, int floor_id) {
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(ModelContracts.SlotContract.ID, slot.getId());
            contentValues.put(ModelContracts.SlotContract.NAME, slot.getName());
            contentValues.put(ModelContracts.SlotContract.COMPANY_NUMBER, slot.getCompany_number());
            contentValues.put(ModelContracts.SlotContract.FLOOR_ID, floor_id);
        }catch (Exception e){
            System.out.println("It's an errror");
        }
        return contentValues;
    }
}

package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.print.Book;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads pilot from JSON data stored in file
// CREDIT: code template from WorkRoomApp from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads pilot from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Pilot read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePilot(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses pilot from JSON object and returns it
    private Pilot parsePilot(JSONObject jsonObject) {

        String name = jsonObject.getString("name");
        JSONArray ratings = jsonObject.getJSONArray("ratings");
        int mednum = jsonObject.getInt("medical#");
        boolean isStudent = jsonObject.getBoolean("student");
        JSONArray bookings = jsonObject.getJSONArray("bookings");
        JSONArray toPostFlight = jsonObject.getJSONArray("to postflight");
        JSONArray completedBookings = jsonObject.getJSONArray("completed bookings");
        JSONArray cancelledBookings = jsonObject.getJSONArray("completed bookings");
        JSONArray pilotLogs = jsonObject.getJSONArray("pilot logs");
        JSONArray listOfPlanes = jsonObject.getJSONArray("list of planes");
        JSONArray listOfInstructors = jsonObject.getJSONArray("list of instructors");
        JSONObject wx = jsonObject.getJSONObject("weather");

        Pilot p = new Pilot();
        p.setName(name);
        addRatings(p, ratings);
        p.setMedNum(mednum);
        p.setStudent(isStudent);
        addBookings(p, bookings);
        addToPostflight(p, toPostFlight);
        addCompletedBookings(p, completedBookings);
        addCancelledBookings(p, cancelledBookings);
        addPilotLogs(p, pilotLogs);
        addPlanes(p, listOfPlanes);
        addInstr(p, listOfInstructors);
        addWx(p, wx);

        return p;
    }

    // MODIFIES: p
    // EFFECTS: parses bookings from JSON array and adds them to pilot's bookings
    private void addBookings(Pilot p, JSONArray bookings) {
        for (Object json : bookings) {
            JSONObject nextBooking = (JSONObject) json;
            addBooking(p, nextBooking);
        }
    }

    // MODIFIES: p
    // EFFECT: parses booking, and adds it to pilot
    private void addBooking(Pilot p, JSONObject booking) {
        Booking parsedBooking = parseBooking(booking);
        p.addBooking(parsedBooking);
    }

    // EFFECTS: parses booking from JSON object and returns it
    private Booking parseBooking(JSONObject booking) {

        JSONObject plane = booking.getJSONObject("plane");
        JSONObject instr = booking.getJSONObject("instructor");
        String day = booking.getString("day");
        String time = booking.getString("time");
        JSONObject pref = booking.getJSONObject("preflight");
        String reasonCancelled = booking.getString("reason cancelled");
        String typeOfLesson = booking.getString("type of lesson");

        Booking b = new Booking();

        addPlane(b, plane);
        addInstr(b, instr);
        b.setDayBooked(day);
        b.setTimeBooked(time);
        addPref(b, pref);
        b.setReasonCancelled(reasonCancelled);
        b.setTypeOfLesson(typeOfLesson);

        return b;
    }

    // MODIFIES: b
    // EFFECT: parses plane & adds plane to booking
    private void addPlane(Booking b, JSONObject plane) {
        Plane parsedPlane = parsePlane(plane);
        b.setPlane(parsedPlane);
    }

    // EFFECT: parses plane from JSON object and returns it
    private Plane parsePlane(JSONObject plane) {
        //        json.put("type", type);
        //        json.put("call sign", callSign);
        //        json.put("availability", avails.toJson());
        //        json.put("hourly rental rate", hourlyRentalRate);
        //        json.put("hourly fuel rate", hourlyFuelRate);
        //        json.put("plane doc", pd.toJson());
        //        json.put("fuel amount", fuelAmount);
        //        json.put("max fuel", maxFuel);

        String type = plane.getString("type");
        String callSign = plane.getString("call sign");
        JSONObject avails = plane.getJSONObject("availability");
        int hourlyRR = plane.getInt("hourly rental rate");
        int hourlyFR = plane.getInt("hourly fuel rate");
        JSONObject pd = plane.getJSONObject("plane doc");
        double fuelA = plane.getDouble("fuel amount");
        double fuelM = plane.getDouble("max fuel");

        Plane plane1 = new Plane();
        plane1.setType(type);
        plane1.setCallSign(callSign);
        addPlaneAvail(plane1, avails);
        plane1.setHourlyRentalRate(hourlyRR);
        plane1.setHourlyFuelRate(hourlyFR);
        addPD(plane1, pd);
        plane1.setFuelAmount(fuelA);
        plane1.setMaxFuel(fuelM);

        return plane1;

    }

    // MODIFIES: plane1
    // EFFECT: parses pd from JSON object, add to plane1
    private void addPD(Plane plane1, JSONObject pd) {
        PlaneDocuments p1 = parsePd(pd);
        plane1.setPd(p1);
    }

    // EFFECT: parses pd from JSON object and returns it
    private PlaneDocuments parsePd(JSONObject pd) {

        JSONArray fls = pd.getJSONArray("flight logs");
        JSONObject ins = pd.getJSONObject("insurance");
        double weight = pd.getDouble("weight");

        PlaneDocuments pd1 = new PlaneDocuments();
        addFls(pd1, fls);
        addIns(pd1, ins);
        pd1.setWeightInfo(weight);

        return pd1;
    }

    // MODIFIES: pd1
    // EFFECT: parses fls from JSON array, add to plane1
    private void addFls(PlaneDocuments pd1, JSONArray fls) {
        for (Object json : fls) {
            JSONObject nextfl = (JSONObject) json;
            addFl(pd1, nextfl);
        }
    }

    // MODIFIES: pd1
    // EFFECTS: parses nextfl from JSON object and adds it to pd1
    private void addFl(PlaneDocuments pd1, JSONObject nextfl) {
        PlaneFlightLog fl = parseFl(nextfl);
        pd1.getFl().add(fl);
    }

    // EFFECT: parses nextfl from JSON object and returns it
    private PlaneFlightLog parseFl(JSONObject nextfl) {

        double hStart = nextfl.getDouble("hobbs start");
        double hEnd = nextfl.getDouble("hobbs end");
        String dAP = nextfl.getString("departing AP");
        String aAP = nextfl.getString("arriving AP");
        double hTilMaint = nextfl.getDouble("hours till' maintenance");

        PlaneFlightLog pfl = new PlaneFlightLog();
        pfl.setHobbsTimeStart(hStart);
        pfl.setHobbsTimeEnd(hEnd);
        pfl.setDepartingAP(dAP);
        pfl.setArrivingAP(aAP);
        pfl.setHoursTillMaint(hTilMaint);

        return pfl;
    }

    // MODIFIES: plane1
    // EFFECT: parses avail from JSON object, add to plane1
    private void addPlaneAvail(Plane plane1, JSONObject avails) {
        DayTime avail = parseAvails(avails);
        plane1.setAvails(avail);
    }

    // EFFECT: parses avails from JSON object and returns it
    private DayTime parseAvails(JSONObject avails) {

        JSONArray mon = avails.getJSONArray("monday");
        JSONArray tues = avails.getJSONArray("tuesday");
        JSONArray wed = avails.getJSONArray("wednesday");
        JSONArray thurs = avails.getJSONArray("thursday");
        JSONArray fri = avails.getJSONArray("friday");
        JSONArray sat = avails.getJSONArray("saturday");
        JSONArray sun = avails.getJSONArray("sunday");

        DayTime dt = new DayTime();
        addMon(dt, mon);
        addTues(dt, tues);
        addWed(dt, wed);
        addThurs(dt, thurs);
        addFri(dt, fri);
        addSat(dt, sat);
        addSun(dt, sun);

        return dt;
    }

    // MODIFIES: dt
    // EFFECT: parses day from JSON object and adds it to dt's monday
    private void addMon(DayTime dt, JSONArray day) {
        for (Object json : day) {
            String nextTime = (String) json;
            dt.getMonday().add(nextTime);
        }
    }

    // MODIFIES: dt
    // EFFECT: parses day from JSON object and adds it to dt's tuesday
    private void addTues(DayTime dt, JSONArray day) {
        for (Object json : day) {
            String nextTime = (String) json;
            dt.getTuesday().add(nextTime);
        }
    }

    // MODIFIES: dt
    // EFFECT: parses day from JSON object and adds it to dt's wednesday
    private void addWed(DayTime dt, JSONArray day) {
        for (Object json : day) {
            String nextTime = (String) json;
            dt.getWednesday().add(nextTime);
        }
    }

    // MODIFIES: dt
    // EFFECT: parses day from JSON object and adds it to dt's thursday
    private void addThurs(DayTime dt, JSONArray day) {
        for (Object json : day) {
            String nextTime = (String) json;
            dt.getThursday().add(nextTime);
        }
    }

    // MODIFIES: dt
    // EFFECT: parses day from JSON object and adds it to dt's friday
    private void addFri(DayTime dt, JSONArray day) {
        for (Object json : day) {
            String nextTime = (String) json;
            dt.getFriday().add(nextTime);
        }
    }

    // MODIFIES: dt
    // EFFECT: parses day from JSON object and adds it to dt's saturday
    private void addSat(DayTime dt, JSONArray day) {
        for (Object json : day) {
            String nextTime = (String) json;
            dt.getSaturday().add(nextTime);
        }
    }

    // MODIFIES: dt
    // EFFECT: parses day from JSON object and adds it to dt's sunday
    private void addSun(DayTime dt, JSONArray day) {
        for (Object json : day) {
            String nextTime = (String) json;
            dt.getSunday().add(nextTime);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses ratings from JSON array and adds them to pilot's ratings
    private void addRatings(Pilot p, JSONArray ratings) {
        for (Object json : ratings) {
            String nextRating = (String) json;
            p.addRating(nextRating);
        }
    }


    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addThingy(WorkRoom wr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Category category = Category.valueOf(jsonObject.getString("category"));
        Thingy thingy = new Thingy(name, category);
        wr.addThingy(thingy);
    }
}

}

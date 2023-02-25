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
        addInstrsPilot(p, listOfInstructors);
        addWx(p, wx);

        return p;
    }

    // MODIFIES: p
    // EFFECTS: parses wx from JSON object and adds them to pilot's weather
    private void addWx(Pilot p, JSONObject wx) {
        Weather parsedWx = parseWx(wx);
        p.setWx(parsedWx);
    }

    // EFFECT: parses weather from JSON object & returns it
    private Weather parseWx(JSONObject wx) {
        // json.put("current METAR", currentMetar);
        //        json.put("current TAF", currentTaf);
        //        json.put("possible METARs", metarsToJson());
        //        json.put("possible TAFs", tafsToJson());

        String metar = wx.getString("current METAR");
        String taf = wx.getString("current TAF");
        JSONArray metars = wx.getJSONArray("possible METARs");
        JSONArray tafs = wx.getJSONArray("possible TAFs");

        Weather weather = new Weather();
        weather.setCurrentMetar(metar);
        weather.setCurrentTaf(taf);
        addMetars(weather, metars);
        addTafs(weather, tafs);

        return weather;
    }

    // MODIFIES: weather
    // EFFECTS: parses tafs from JSON array and adds them to weather
    private void addTafs(Weather weather, JSONArray tafs) {
        for (Object json : tafs) {
            String nextTaf = (String) json;
            weather.getTafs().add(nextTaf);
        }
    }

    // MODIFIES: weather
    // EFFECTS: parses metars from JSON array and adds them to weather
    private void addMetars(Weather weather, JSONArray metars) {
        for (Object json : metars) {
            String nextMet = (String) json;
            weather.getMetars().add(nextMet);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses instructors from JSON array and adds them to pilot's list of instructors
    private void addInstrsPilot(Pilot p, JSONArray listOfInstructors) {
        for (Object json : listOfInstructors) {
            JSONObject nextInstr = (JSONObject) json;
            addInstrPilot(p, nextInstr);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses instructor from JSON object and adds them to pilot's list of instructors
    private void addInstrPilot(Pilot p, JSONObject nextInstr) {
        Instructor parsedInstructor = parseInstr(nextInstr);
        p.getLoi().add(parsedInstructor);
    }

    // MODIFIES: p
    // EFFECTS: parses planes from JSON array and adds them to pilot's list of planes
    private void addPlanes(Pilot p, JSONArray listOfPlanes) {
        for (Object json : listOfPlanes) {
            JSONObject nextPlane = (JSONObject) json;
            addPlanePilot(p, nextPlane);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses plane from JSON object and adds them to pilot's list of planes
    private void addPlanePilot(Pilot p, JSONObject nextPlane) {
        Plane parsedPlane = parsePlane(nextPlane);
        p.getLop().add(parsedPlane);
    }

    // MODIFIES: p
    // EFFECTS: parses pilot logs from JSON array and adds them to pilot's logs
    private void addPilotLogs(Pilot p, JSONArray pilotLogs) {
        for (Object json : pilotLogs) {
            JSONObject nextPL = (JSONObject) json;
            addPL(p, nextPL);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses pilot log from JSON object and adds them to pilot's pilot logs
    private void addPL(Pilot p, JSONObject nextPL) {
        PilotLog parsedPl = parsePl(nextPL);
        p.getPl().add(parsedPl);
    }

    // EFFECT: parses pilot log from JSON object & returns it
    private PilotLog parsePl(JSONObject nextPL) {
        
        String day1 = nextPL.getString("day");
        String time1 = nextPL.getString("time");
        double ft = nextPL.getDouble("flight time");
        String pilotType = nextPL.getString("piloting type");
        String planeType = nextPL.getString("plane type");
        String planeCallS = nextPL.getString("plane call sign");
        
        PilotLog pl1 = new PilotLog();
        pl1.setFlightTime(ft);
        pl1.setPlaneType(planeType);
        pl1.setTime(time1);
        pl1.setDay(day1);
        pl1.setTypeOfPiloting(pilotType);
        pl1.setPlaneCallSign(planeCallS);
        
        return pl1;
    }

    // MODIFIES: p
    // EFFECTS: parses bookings for cancelled from JSON array and adds them to pilot's cancelled bookings
    private void addCancelledBookings(Pilot p, JSONArray cancelledBookings) {
        for (Object json : cancelledBookings) {
            JSONObject nextBooking = (JSONObject) json;
            addCancelled(p, nextBooking);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses bookings for cancelled bookings from JSON array and adds them to pilot's cancelled bookings list
    private void addCancelled(Pilot p, JSONObject nextBooking) {
        Booking parsedBooking = parseBooking(nextBooking);
        p.getCancelled().add(parsedBooking);
    }

    // MODIFIES: p
    // EFFECTS: parses bookings for completed from JSON array and adds them to pilot's completed bookings
    private void addCompletedBookings(Pilot p, JSONArray completedBookings) {
        for (Object json : completedBookings) {
            JSONObject nextBooking = (JSONObject) json;
            addCompleted(p, nextBooking);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses bookings for completed bookings from JSON array and adds them to pilot's completed bookings list
    private void addCompleted(Pilot p, JSONObject nextBooking) {
        Booking parsedBooking = parseBooking(nextBooking);
        p.getCompletedBookings().add(parsedBooking);
    }

    // MODIFIES: p
    // EFFECTS: parses bookings for postflight from JSON array and adds them to pilot's postflight list
    private void addToPostflight(Pilot p, JSONArray toPostFlight) {
        for (Object json : toPostFlight) {
            JSONObject nextBooking = (JSONObject) json;
            addPostFlight(p, nextBooking);
        }
    }

    // MODIFIES: p
    // EFFECT: parses booking, and adds it to pilot's postflight list
    private void addPostFlight(Pilot p, JSONObject booking) {
        Booking parsedBooking = parseBooking(booking);
        p.getToPostFlight().add(parsedBooking);
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
    // EFFECT: parses preflight & adds it to booking b
    private void addPref(Booking b, JSONObject pref) {
        Preflight pf = parsePref(pref);
        b.setPref(pf);
    }

    // EFFECT: parses preflight from JSON object & returns it
    private Preflight parsePref(JSONObject pref) {

        boolean doc = pref.getBoolean("doc onboard");
        boolean fire = pref.getBoolean("check fire extinguisher");
        boolean walk = pref.getBoolean("walk around");
        boolean fuel = pref.getBoolean("fuel enough");
        boolean wb = pref.getBoolean("w&b done");
        boolean passBrief = pref.getBoolean("passenger brief");
        boolean insVal = pref.getBoolean("insurance validity");
        double hobbsStart = pref.getDouble("hobbs start");
        String depAP = pref.getString("depart AP");

        Preflight pf = new Preflight();
        pf.setDocOnBoard(doc);
        pf.setCheckedFireExt(fire);
        pf.setWalkAroundDone(walk);
        pf.setFuelEnough(fuel);
        pf.setWBDone(wb);
        pf.setPassengerBriefDone(passBrief);
        pf.setInsuranceValid(insVal);
        pf.setHobbsTimeStart(hobbsStart);
        pf.setDepartAP(depAP);

        return pf;
    }

    // MODIFIES: b
    // EFFECT: parses instructor & adds it to booking b
    private void addInstr(Booking b, JSONObject instr) {
        Instructor instr1 = parseInstr(instr);
        b.setInstructor(instr1);
    }


    // EFFECT: parses instructor from JSON object & returns it
    private Instructor parseInstr(JSONObject instr) {

        String insName = instr.getString("name");
        String insClass = instr.getString("class");
        JSONArray ratings = instr.getJSONArray("ratings");
        JSONObject avails = instr.getJSONObject("availability");
        int hourlyR = instr.getInt("hourly rate");
        int experience = instr.getInt("years of experience");

        Instructor i1 = new Instructor();
        i1.setName(insName);
        i1.setInstrClass(insClass);
        addRatings(i1, ratings);
        addInstrAvail(i1, avails);
        i1.setHourlyRate(hourlyR);
        i1.setExpYears(experience);

        return i1;
    }

    // MODIFIES: i
    // EFFECT: parses avail from JSON object, add to instructor i
    private void addInstrAvail(Instructor i, JSONObject avails) {
        DayTime avail = parseAvails(avails);
        i.setAvails(avail);
    }

    // MODIFIES: b
    // EFFECT: parses plane & adds plane to booking
    private void addPlane(Booking b, JSONObject plane) {
        Plane parsedPlane = parsePlane(plane);
        b.setPlane(parsedPlane);
    }

    // EFFECT: parses plane from JSON object and returns it
    private Plane parsePlane(JSONObject plane) {

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
    // EFFECT: parses ins from JSON object, add to pd1
    private void addIns(PlaneDocuments pd1, JSONObject ins) {
        Insurance ins1 = parseIns(ins);
        pd1.setInsurance(ins1);
    }

    // EFFECT: parses ins from JSON object and returns it
    private Insurance parseIns(JSONObject ins) {

        String vd = ins.getString("validity start");
        String vde = ins.getString("validity end");
        int amount = ins.getInt("amount");
        String type = ins.getString("type");

        Insurance insurance = new Insurance();
        insurance.setDateValid(vd);
        insurance.setDateValidUntil(vde);
        insurance.setAmountInsured(amount);
        insurance.setTypeOfInsurance(type);

        return insurance;
    }

    // MODIFIES: pd1
    // EFFECT: parses fls from JSON array, add to pd1
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

        double hobbsStart = nextfl.getDouble("hobbs start");
        double hobbsEnd = nextfl.getDouble("hobbs end");
        String depAP = nextfl.getString("departing AP");
        String arrAP = nextfl.getString("arriving AP");
        double hoursTilMaint = nextfl.getDouble("hours till' maintenance");

        PlaneFlightLog pfl = new PlaneFlightLog();
        pfl.setHobbsTimeStart(hobbsStart);
        pfl.setHobbsTimeEnd(hobbsEnd);
        pfl.setDepartingAP(depAP);
        pfl.setArrivingAP(arrAP);
        pfl.setHoursTillMaint(hoursTilMaint);

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

    // MODIFIES: i
    // EFFECTS: parses ratings from JSON array and adds them to instructor's ratings
    private void addRatings(Instructor i, JSONArray ratings) {
        for (Object json : ratings) {
            String nextRating = (String) json;
            i.getRatings().add(nextRating);
        }
    }
}


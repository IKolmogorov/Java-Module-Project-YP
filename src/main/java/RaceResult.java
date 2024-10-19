public class RaceResult {
    Car car;
    int raceDistance;
    int racePlace;

    public RaceResult(Car car, int raceDistance) {
        this.car = car;
        this.raceDistance = raceDistance;
    }

    public int getRaceDistance(){
        return this.raceDistance;
    }
}

package dao;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "POINTS")
public class Point {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "AUTO_ID")
    private String autoId;

    @Column(name = "LATITUDE")
    private double lat;
    @Column(name = "LONGITUDE")
    private double lon;
    @Column(name = "TIME")
    private long time;
    @Column(name = "SPEED")
    private double speed;
    @Column(name = "AZIMUTH")
    private double azimuth;
    @Column(name = "ISSEND")
    private boolean isSend = false;

    public boolean isSend(boolean send) {
        return isSend;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}

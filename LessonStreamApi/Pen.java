package LessonStreamApi;

import java.util.Objects;

public class Pen implements Comparable <Pen> {
    private final double length;
    private final String company;

    public Pen(double length, String company) {
        this.length = length;
        this.company = company;
    }

    @Override
    public int compareTo(Pen o) {
        if (o.length > length) //TODO Double.compare(i1, i2)
            return -1;
        else {
            if (length == o.length)
                return 0;
            else
                return 1;
        }
    }

    public double getLength() {
        return length;
    }
    public String getCompany() {
        return company;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pen pen = (Pen) o;
        return Double.compare(pen.length, length) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length);
    }

    @Override
    public String toString() {
        return "Pen{" +
                "length=" + length +
                '}';
    }
}

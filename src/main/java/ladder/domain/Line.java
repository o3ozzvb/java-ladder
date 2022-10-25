package ladder.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Line {

    private List<Point> points;

    public Line(List<Point> points) {
        this.points = points;
    }

    public static Line of(int countOfPerson) {
        List<Point> points = new ArrayList<>();

        points.add(new Point());
        for (int i = 1; i < countOfPerson; i++) {
            points.add(points.get(i - 1).nextPoint());
        }

        return new Line(points);
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<String> getResultLine() {
        return points.stream()
                .map(point -> point.getPointExist())
                .collect(Collectors.toList());
    }
}
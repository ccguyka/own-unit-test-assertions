package org.ccguyka.testwatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/**
 * Prints the top 10 most time consuming test methods to console output.
 * <p>
 * Failed tests are ignored.
 * </p>
 * Usage:
 * <pre>
 * &lt;plugin&gt;
 *   &lt;groupId&gt;org.apache.maven.plugins&lt;/groupId&gt;
 *   &lt;artifactId&gt;maven-surefire-plugin&lt;/artifactId&gt;
 *   &lt;version&gt;2.19.1&lt;/version&gt;
 *   &lt;configuration&gt;
 *     &lt;properties&gt;
 *       &lt;property&gt;
 *         &lt;name&gt;listener&lt;/name&gt;
 *         &lt;value&gt;org.ccguyka.testwatcher.TopTenExecutionTimes&lt;/value&gt;
 *       &lt;/property&gt;
 *     &lt;/properties&gt;
 *   &lt;/configuration&gt;
 * &lt;/plugin&gt;
 * </pre>
 */
@RunListener.ThreadSafe
public class TopTenExecutionTimes extends RunListener {

    private final Map<String, Execution> executionTimes = new ConcurrentHashMap<>();

    @Override
    public void testStarted(Description description) throws Exception {
        executionTimes.put(getKeyFor(description), new Execution());
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        executionTimes.remove(getKeyFor(failure.getDescription()));
    }

    @Override
    public void testFinished(Description description) throws Exception {
        Execution executionTime = executionTimes.get(getKeyFor(description));
        if (executionTime != null) {
            executionTime.finish();
        }
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        List<Map.Entry<String, Execution>> top10 = getTopTen();

        print(top10);
    }

    private void print(List<Map.Entry<String, Execution>> top10) {
        StringBuilder text = new StringBuilder();
        text.append('\n');
        text.append("Top 10\n");

        for (Entry<String, Execution> entry : top10) {
            text.append(getDurationText(entry.getValue().getDuration()) + "  " + entry.getKey());
            text.append('\n');
        }

        System.out.print(text.toString());
    }

    private List<Map.Entry<String, Execution>> getTopTen() {
        List<Map.Entry<String, Execution>> entries = new ArrayList<>(executionTimes.entrySet());
        entries.sort((o1, o2) -> o2.getValue().getDuration().compareTo(o1.getValue().getDuration()));
        return entries.subList(0, Math.min(entries.size(), 10));
    }

    private String getKeyFor(Description description) {
        return description.getTestClass().getName() + "." + description.getMethodName();
    }

    private String getDurationText(Long duration) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;
        long millis = TimeUnit.MILLISECONDS.toMillis(duration) % 1000;

        return String.format("%02d:%02d:%03d", minutes, seconds, millis);
    }

    private class Execution {
        final Long start;
        Long end;

        private Execution() {
            start = System.currentTimeMillis();
        }

        void finish() {
            end = System.currentTimeMillis();
        }

        Long getDuration() {
            return end - start;
        }
    }
}

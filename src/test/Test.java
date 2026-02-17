package test;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;
import java.io.PrintWriter;


/**
 * A simple test runner that executes all tests in the com.carrental package
 * and prints a summary to the console.
 */
public class Test {
    public static void main(String[] args) {
        // 1. Build a request to scan the package containing your tests
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectPackage("test")) // scans all tests in this package and subpackages
                .build();

        // 2. Create a launcher and a listener to collect results
        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();

        // 3. Execute tests with the listener attached
        launcher.execute(request, listener);

        // 4. Get the summary and print it
        TestExecutionSummary summary = listener.getSummary();
        summary.printTo(new PrintWriter(System.out));

        // 5. Exit with appropriate code (0 for success, 1 for failures)
        if (summary.getTestsFailedCount() > 0) {
            System.exit(1);
        }
    }
}

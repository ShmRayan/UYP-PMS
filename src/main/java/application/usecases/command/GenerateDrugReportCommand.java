package application.usecases.command;

public class GenerateDrugReportCommand {

    public final String startDate;
    public final String endDate;
    public final String din;

    public GenerateDrugReportCommand(String startDate, String endDate, String din) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.din = din;
    }
}

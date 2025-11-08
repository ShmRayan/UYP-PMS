package application.usecases.interfaces;

import application.usecases.command.GenerateDrugReportCommand;
import domain.reporting.DrugReport;

public interface GenerateDrugReportUseCase {
    DrugReport execute(GenerateDrugReportCommand command);
}

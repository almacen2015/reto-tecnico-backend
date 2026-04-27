package backend.msaccount.presentation.controller;

import backend.msaccount.application.usecase.GenerateReportUseCase;
import backend.msaccount.presentation.dto.ReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final GenerateReportUseCase useCase;

    @GetMapping("/{clientId}")
    public Mono<ResponseEntity<ReportResponse>> generateReport(
            @PathVariable("clientId") Long clientId,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate
    ) {
        return Mono.fromCallable(() -> useCase.execute(clientId, startDate, endDate))
                .subscribeOn(Schedulers.boundedElastic())
                .map(ResponseEntity::ok);
    }
}

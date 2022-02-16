package com.library.management.bookReservationService.controller;

import com.library.management.bookReservationService.model.ReserveRequest;
import com.library.management.bookReservationService.model.ReserveRequestList;
import com.library.management.bookReservationService.service.ReserveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReserveController {

    @Autowired
    ReserveService reserveService;

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @GetMapping("/{id}")
    @PostMapping("/bookreservationservice/reservebook")
    public String bookReserve(@RequestBody ReserveRequest reserveRequest){
        return reserveService.bookReserve(reserveRequest);
    }

    @GetMapping("/bookreservationservice/reservebook/bookid/{bookId}")
    public ReserveRequestList getReserveBookListByBookId(@PathVariable String bookId){
        return reserveService.getReserveBookListByBookId(bookId);
    }
}

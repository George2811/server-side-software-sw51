package com.acme.perustars.controller;

import com.acme.perustars.domain.model.Artist;
import com.acme.perustars.domain.model.ClaimTicket;
import com.acme.perustars.resource.ArtistResource;
import com.acme.perustars.resource.ClaimTicketResource;
import com.acme.perustars.resource.SaveArtistResource;
import com.acme.perustars.resource.SaveClaimTicketResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ArtistClaimTicketsController {

    // @Autowired
    //private ClaimTicketService claimTicketService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get All Claim Tickets", description = "Get All ClaimTickets By ArtistId", tags = {"ClaimTickets"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All ClaimTickets returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/artists/{artistId}/claimTickets")
    public Page<ClaimTicketResource> getAllClaimTicketsByArtistId(@PathVariable Long artistId){
        return null;
    }


    @Operation(summary = "Get ClaimTicket By Id", description = "Get ClaimTicket By Id And ArtistId", tags = {"ClaimTickets"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ClaimTicket returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/artists/{artistId}/claimTickets/{claimTicketId}")
    public ClaimTicketResource getClaimTicketByIdAndArtistId(@PathVariable Long artistId, @PathVariable Long claimTicketId) {
        return null;
    }


    @Operation(summary = "Post ClaimTicket", description = "Create a ClaimTicket", tags = {"ClaimTicket"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ClaimTicket created", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/artists/{artistId}/claimTickets")
    public ClaimTicketResource createClaimTicket(@PathVariable Long artistId, @Valid @RequestBody SaveClaimTicketResource resource) {
        return null;
    }


    @Operation(summary = "Put ClaimTicket", description = "Update a ClaimTicket", tags = {"ClaimTickets"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ClaimTicket updated", content = @Content(mediaType =
                    "application/json"))
    })
    @PutMapping("/artists/{artistId}/claimTickets/{claimTicketId}")
    public ArtistResource updateClaimTicket(@PathVariable Long artistId,@PathVariable Long claimTicketId, @RequestBody SaveArtistResource resource) {
        return null;
    }


    @Operation(summary = "Delete ClaimTicket", description = "Delete a ClaimTicket", tags = {"ClaimTickets"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ClaimTicket deleted", content = @Content(mediaType =
                    "application/json"))
    })
    @DeleteMapping("/artists/{artistId}/claimTickets/{claimTicketId}")
    public ResponseEntity<?> deleteClaimTicket(@PathVariable Long artistId, @PathVariable Long claimTicketId) {
        return null;
    }

    @Operation(summary = "Get All Reports", description = "Get All Reports By ArtistId", tags = {"Reports"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Reports returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/artists/{artistId}/reports")
    public Page<ClaimTicketResource> getAllReportsByArtistId(@PathVariable Long artistId){
        return null;
    }

    private ClaimTicket convertToEntity(SaveClaimTicketResource resource){
        return mapper.map(resource, ClaimTicket.class);
    }
    private ClaimTicketResource convertToResource(ClaimTicket entity){
        return mapper.map(entity, ClaimTicketResource.class);
    }
}

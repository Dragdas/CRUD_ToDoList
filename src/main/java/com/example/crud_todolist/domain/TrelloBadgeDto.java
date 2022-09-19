package com.example.crud_todolist.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class TrelloBadgeDto {

    @JsonProperty("attachmentsByType")
    private TrelloDto attachmentsByType;

    @JsonProperty("location")
    private boolean location;

    @JsonProperty("votes")
    private long votes;

    @JsonProperty("viewingMemberVoted")
    private boolean viewingMemberVoted;

    @JsonProperty("subscribed")
    private boolean subscribed;

    @JsonProperty("fogbugz")
    private String fogbugz;

    @JsonProperty("checkItems")
    private long checkItems;

    @JsonProperty("checkItemsChecked")
    private long checkItemsChecked;

    @JsonProperty("checkItemsEarliestDue")
    private LocalDateTime checkItemsEarliestDue;

    @JsonProperty("comments")
    private long comments;

    @JsonProperty("attachments")
    private long attachments;

    @JsonProperty("description")
    private boolean description;

    @JsonProperty("due")
    private LocalDateTime due;

    @JsonProperty("dueComplete")
    private boolean dueComplete;

    @JsonProperty("start")
    private LocalDateTime start;


}

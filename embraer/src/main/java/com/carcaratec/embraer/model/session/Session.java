package com.carcaratec.embraer.model.session;


import com.carcaratec.embraer.model.dto.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "session_log")
@Data
public class Session {

    @Id
    @GeneratedValue
    private UUID sessionId;


    private UUID userId;

    private Timestamp loginTime;

    private Timestamp logoutTime;
}

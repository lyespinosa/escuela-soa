package com.example.notuls.entities.projections;

import java.time.LocalDateTime;
import java.util.Date;

public interface CollectionProjection {

    String getName();

    String getImage();

    Date getUpdatedAt();

    Date getCreatedAt();

    Long getId();
}

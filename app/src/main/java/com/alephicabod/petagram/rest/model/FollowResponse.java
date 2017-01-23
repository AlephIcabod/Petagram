package com.alephicabod.petagram.rest.model;

/**
 * Created by angel on 22/01/2017.
 */

public class FollowResponse {
private String outgoing_status,incoming_status;
    boolean target_user_is_private;

    public FollowResponse(String outgoing_status, String incoming_status, boolean target_user_is_private) {
        this.outgoing_status = outgoing_status;
        this.incoming_status = incoming_status;
        this.target_user_is_private = target_user_is_private;
    }

    public String getOutgoing_status() {
        return outgoing_status;
    }

    public void setOutgoing_status(String outgoing_status) {
        this.outgoing_status = outgoing_status;
    }

    public String getIncoming_status() {
        return incoming_status;
    }

    public void setIncoming_status(String incoming_status) {
        this.incoming_status = incoming_status;
    }

    public boolean isTarget_user_is_private() {
        return target_user_is_private;
    }

    public void setTarget_user_is_private(boolean target_user_is_private) {
        this.target_user_is_private = target_user_is_private;
    }
}

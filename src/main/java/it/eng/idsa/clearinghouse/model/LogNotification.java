package it.eng.idsa.clearinghouse.model;

import de.fraunhofer.iais.eis.*;

public class LogNotification {

    private Message header;
    private Body body;

    public LogNotification(Message header, Body body) {
        build(header);
        this.body = body;
    }

    private void build(Message header) {
        if (header instanceof RejectionMessage) {
            RejectionMessage rejectionMessage = ((RejectionMessage) header);
            this.header = new RejectionMessageBuilder(rejectionMessage.getId())
                    ._modelVersion_(rejectionMessage.getModelVersion())
                    ._issued_(rejectionMessage.getIssued())
                    ._correlationMessage_(rejectionMessage.getCorrelationMessage())
                    ._issuerConnector_(rejectionMessage.getIssuerConnector())
                    ._recipientConnector_(rejectionMessage.getRecipientConnector())
                    ._senderAgent_(rejectionMessage.getSenderAgent())
                    ._recipientAgent_(null)
                    ._transferContract_(rejectionMessage.getTransferContract())
                    ._securityToken_(null) //mandatory in SPECS but non suitable for Blockchain
                    ._authorizationToken_(null)
                    ._rejectionReason_(rejectionMessage.getRejectionReason())
                    ._contentVersion_(null)
                    .build();

        } else if (header instanceof QueryMessage) {
            QueryMessage queryMessage = ((QueryMessage) header);
            this.header = new QueryMessageBuilder(queryMessage.getId())
                    ._modelVersion_(queryMessage.getModelVersion())
                    ._issued_(queryMessage.getIssued())
                    ._correlationMessage_(queryMessage.getCorrelationMessage())
                    ._issuerConnector_(queryMessage.getIssuerConnector())
                    ._recipientConnector_(queryMessage.getRecipientConnector())
                    ._senderAgent_(queryMessage.getSenderAgent())
                    ._recipientAgent_(null)
                    ._transferContract_(queryMessage.getTransferContract())
                    ._securityToken_(null) //mandatory in SPECS but non suitable for Blockchain
                    ._authorizationToken_(null)
                    ._queryLanguage_(queryMessage.getQueryLanguage())
                    ._queryScope_(queryMessage.getQueryScope())
                    ._contentVersion_(null)
                    .build();

        } else if (header instanceof ResponseMessage
                || header instanceof NotificationMessage
                || header instanceof Message) {
            this.header = new MessageBuilder(header.getId())
                    ._modelVersion_(header.getModelVersion())
                    ._issued_(header.getIssued())
                    ._correlationMessage_(header.getCorrelationMessage())
                    ._issuerConnector_(header.getIssuerConnector())
                    ._recipientConnector_(header.getRecipientConnector())
                    ._senderAgent_(header.getSenderAgent())
                    ._recipientAgent_(null)
                    ._transferContract_(header.getTransferContract())
                    ._securityToken_(null) //mandatory in SPECS but non suitable for Blockchain
                    ._authorizationToken_(null)
                    ._contentVersion_(null)
                    .build();
        }
    }

    public Message getMessage() {
        return header;
    }

    public Body getBody() {
        return body;
    }

}
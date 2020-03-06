package it.eng;

import de.fraunhofer.iais.eis.*;
import it.eng.idsa.clearinghouse.model.Body;
import it.eng.idsa.clearinghouse.model.LogNotification;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testRejectionMessageModel() {
        RejectionMessageBuilder messageBuilder = new RejectionMessageBuilder();
        RejectionMessage rejectionMessage = messageBuilder._rejectionReason_(RejectionReason.TEMPORARILY_NOT_AVAILABLE).build();
        Body body = new Body("test");
        LogNotification logNotification = new LogNotification(rejectionMessage, body);
        RejectionMessage rejectionMessageExpected = (RejectionMessage) logNotification.getMessage();
        Assert.assertEquals(rejectionMessageExpected.getRejectionReason(), rejectionMessage.getRejectionReason());
    }

    @Test
    public void testQueryMessageModel() {
        QueryMessageBuilder messageBuilder = new QueryMessageBuilder();
        QueryMessage queryMessage = messageBuilder._queryLanguage_(QueryLanguage.SPARQL).build();
        Body body = new Body("test");
        LogNotification logNotification = new LogNotification(queryMessage, body);
        QueryMessage queryMessageExpected = (QueryMessage) logNotification.getMessage();
        Assert.assertEquals(queryMessageExpected.getQueryLanguage(), queryMessage.getQueryLanguage());
    }


}

import com.comptel.mds.sas.java_macroserver.Arglist;

import com.comptel.mds.sas.java_macroserver.Status;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.rt.fsom.nei.eapi.Connection;
import ru.rt.fsom.nei.eapi.dict.Params;
import ru.rt.fsom.nei.eapi.utils.SOAPLogger;
import ru.rt.oms.Fault;
import ru.rt.oms.TFault;

import javax.xml.soap.SOAPFault;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class MacroEAPIEAPI34Test {

    private macro_EAPI_EAPI_34 macro;
    Status status;

    @Before
    public void setUp() {
        macro = new macro_EAPI_EAPI_34();
    }

    @Test
    public void testInit() {
        status = macro.init(null);
        assertNotNull(status);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskOk, status.getTaskStatus());
    }

    // TODO возможно, имеет смысл сделать проверку обязательных параметров?
    @Test
    public void testLogin() {
        status = macro.login(null);
        assertNotNull(status);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskOk, status.getTaskStatus());
    }

    @Test
    public void testLogout() {
        Status status = macro.logout(null);
        assertNotNull(status);
        assertEquals(Status.connClosed, status.getConnStatus());
        assertEquals(Status.taskOk, status.getTaskStatus());
    }

    // не заданы аргументы
    @Test(expected=NullPointerException.class)
    public void testCreateNullArgs() {
        macro.create(null);
    }

    // недопустимые аргументы
    @Test
    public void testCreateInvalidParams() throws MalformedURLException {
        Arglist arglist = new Arglist();
        arglist.setArg("foo", "bar");
        Status status = macro.create(arglist);
        assertNotNull(status);
        assertEquals(Status.connFailed, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // недопустимое значение таймаута
    @Test
    public void testCreateTimeoutOutOfRange() {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12");
        Status status = macro.create(arglist);
        assertNotNull(status);
        assertEquals(Status.connFailed, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // не задан аргумент task_type
    @Test
    public void testCreateMissingTaskType() throws MalformedURLException {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        Status status = macro.create(arglist);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // неправильное значение аргумента TASK_TYPE
    @Test
    public void testCreateInvalidTaskType() {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "1");
        Status status = macro.create(arglist);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // не задан аргумент ACTION
    @Test
    public void testCreateMissingAction() {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        Status status = macro.create(arglist);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // неправильное значение аргумента ACTION
    @Test
    public void testCreateInvalidAction() {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", "foo");
        Status status = macro.create(arglist);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // не найден конфигурационный файл
    @Test
    public void testCreateMissingConfig() {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", Params.ACTION_CHECK_ACCESS_CAPABILITY);
        Status status = macro.create(arglist);
        assertEquals(Status.connFailed, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // без кода возврата
    @Test
    public void testCreateWithoutRc() throws Exception {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", Params.ACTION_CHECK_ACCESS_CAPABILITY);

        Connection connection = macro.getConnection(macro);
        Connection spyConnection = spy(connection);

        Arglist outputArgs = new Arglist();
        outputArgs.setArg("foo", "bar");
        doReturn(outputArgs).when(spyConnection).checkAccessCapability(any(Arglist.class), any(SOAPLogger.class), anyInt());
        macro.setConnection(spyConnection);
        Status status = macro.create(arglist);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskOk, status.getTaskStatus());
    }

    // кода возврата = 0
    @Test
    public void testCreateRcValid() throws Exception {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", Params.ACTION_CHECK_ACCESS_CAPABILITY);

        Connection connection = macro.getConnection(macro);
        Connection spyConnection = spy(connection);

        Arglist outputArgs = new Arglist();
        outputArgs.setArg("foo", "bar");
        outputArgs.setArg(Params.ORDER_RESULT_CODE, "0");
        doReturn(outputArgs).when(spyConnection).checkAccessCapability(any(Arglist.class), any(SOAPLogger.class), anyInt());
        macro.setConnection(spyConnection);
        Status status = macro.create(arglist);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskOk, status.getTaskStatus());
    }

    // кода возврата != 0
    @Test
    public void testCreateRcInvalid() throws Exception {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", Params.ACTION_CHECK_ACCESS_CAPABILITY);

        Connection connection = macro.getConnection(macro);
        Connection spyConnection = spy(connection);

        Arglist outputArgs = new Arglist();
        outputArgs.setArg("foo", "bar");
        outputArgs.setArg(Params.ORDER_RESULT_CODE, "1");
        doReturn(outputArgs).when(spyConnection).checkAccessCapability(any(Arglist.class), any(SOAPLogger.class), anyInt());
        macro.setConnection(spyConnection);
        Status status = macro.create(arglist);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // exception: Fault
    @Test
    public void testCreateThrowFault() throws Exception {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", Params.ACTION_CHECK_ACCESS_CAPABILITY);

        Connection connection = macro.getConnection(macro);
        Connection spyConnection = spy(connection);

        Arglist outputArgs = new Arglist();
        outputArgs.setArg("foo", "bar");
        outputArgs.setArg(Params.ORDER_RESULT_CODE, "1");
        TFault faultInfo = new TFault();
        faultInfo.setMessage("message");
        faultInfo.setResultCode(BigInteger.ONE);
        Fault exception = new Fault("fault", faultInfo);

        doThrow(exception).when(spyConnection).checkAccessCapability(any(Arglist.class), any(SOAPLogger.class), anyInt());
        macro.setConnection(spyConnection);
        Status status = macro.create(arglist);
        assertEquals(Status.connFailed, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // exception: MalformedURLException
    @Test
    public void testCreateThrowMalformedURLException() throws Exception {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", Params.ACTION_CHECK_ACCESS_CAPABILITY);

        Connection connection = macro.getConnection(macro);
        Connection spyConnection = spy(connection);

        Arglist outputArgs = new Arglist();
        outputArgs.setArg("foo", "bar");
        outputArgs.setArg(Params.ORDER_RESULT_CODE, "1");
        MalformedURLException exception = new MalformedURLException();

        doThrow(exception).when(spyConnection).checkAccessCapability(any(Arglist.class), any(SOAPLogger.class), anyInt());
        macro.setConnection(spyConnection);
        Status status = macro.create(arglist);
        assertEquals(Status.connFailed, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // exception: IOException
    @Test
    public void testCreateThrowIOException() throws Exception {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", Params.ACTION_CHECK_ACCESS_CAPABILITY);

        Connection connection = macro.getConnection(macro);
        Connection spyConnection = spy(connection);

        Arglist outputArgs = new Arglist();
        outputArgs.setArg("foo", "bar");
        outputArgs.setArg(Params.ORDER_RESULT_CODE, "1");
        IOException exception = new IOException();

        doThrow(exception).when(spyConnection).checkAccessCapability(any(Arglist.class), any(SOAPLogger.class), anyInt());
        macro.setConnection(spyConnection);
        Status status = macro.create(arglist);
        assertEquals(Status.connFailed, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // exception: IllegalAccessException
    @Test
    public void testCreateThrowIllegalAccessException() throws Exception {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", Params.ACTION_CHECK_ACCESS_CAPABILITY);

        Connection connection = macro.getConnection(macro);
        Connection spyConnection = spy(connection);

        Arglist outputArgs = new Arglist();
        outputArgs.setArg("foo", "bar");
        outputArgs.setArg(Params.ORDER_RESULT_CODE, "1");
        IllegalAccessException exception = new IllegalAccessException();

        doThrow(exception).when(spyConnection).checkAccessCapability(any(Arglist.class), any(SOAPLogger.class), anyInt());
        macro.setConnection(spyConnection);
        Status status = macro.create(arglist);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // exception: IllegalArgumentException
    @Test
    public void testCreateThrowIllegalArgumentException() throws Exception {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", Params.ACTION_CHECK_ACCESS_CAPABILITY);

        Connection connection = macro.getConnection(macro);
        Connection spyConnection = spy(connection);

        Arglist outputArgs = new Arglist();
        outputArgs.setArg("foo", "bar");
        outputArgs.setArg(Params.ORDER_RESULT_CODE, "1");
        IllegalArgumentException exception = new IllegalArgumentException();

        doThrow(exception).when(spyConnection).checkAccessCapability(any(Arglist.class), any(SOAPLogger.class), anyInt());
        macro.setConnection(spyConnection);
        Status status = macro.create(arglist);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // exception: InvocationTargetException
    @Test
    public void testCreateInvocationTargetException() throws Exception {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", Params.ACTION_CHECK_ACCESS_CAPABILITY);

        Connection connection = macro.getConnection(macro);
        Connection spyConnection = spy(connection);

        Arglist outputArgs = new Arglist();
        outputArgs.setArg("foo", "bar");
        outputArgs.setArg(Params.ORDER_RESULT_CODE, "1");
        InvocationTargetException exception = new InvocationTargetException(new Throwable());

        doThrow(exception).when(spyConnection).checkAccessCapability(any(Arglist.class), any(SOAPLogger.class), anyInt());
        macro.setConnection(spyConnection);
        Status status = macro.create(arglist);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // exception: SOAPFaultException
    @Test
    public void testCreateSOAPFaultException() throws Exception {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", Params.ACTION_CHECK_ACCESS_CAPABILITY);

        Connection connection = macro.getConnection(macro);
        Connection spyConnection = spy(connection);

        Arglist outputArgs = new Arglist();
        outputArgs.setArg("foo", "bar");
        outputArgs.setArg(Params.ORDER_RESULT_CODE, "1");
        SOAPFaultException exception = new SOAPFaultException(mock(SOAPFault.class));

        doThrow(exception).when(spyConnection).checkAccessCapability(any(Arglist.class), any(SOAPLogger.class), anyInt());
        macro.setConnection(spyConnection);
        Status status = macro.create(arglist);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // exception: WebServiceException
    @Test
    public void testCreateWebServiceException() throws Exception {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", Params.ACTION_CHECK_ACCESS_CAPABILITY);

        Connection connection = macro.getConnection(macro);
        Connection spyConnection = spy(connection);

        Arglist outputArgs = new Arglist();
        outputArgs.setArg("foo", "bar");
        outputArgs.setArg(Params.ORDER_RESULT_CODE, "1");
        WebServiceException exception = new WebServiceException();

        doThrow(exception).when(spyConnection).checkAccessCapability(any(Arglist.class), any(SOAPLogger.class), anyInt());
        macro.setConnection(spyConnection);
        Status status = macro.create(arglist);
        assertEquals(Status.connFailed, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }

    // exceptions: другие
    @Test
    public void testCreateOtherException() throws Exception {
        Arglist arglist = new Arglist();
        arglist.setArg(macro_EAPI_EAPI_34.NEI_SYNC_RESP_TIMEOUT, "12000");
        arglist.setArg("task_type", "create");
        arglist.setArg("ACTION", Params.ACTION_CHECK_ACCESS_CAPABILITY);

        Connection connection = macro.getConnection(macro);
        Connection spyConnection = spy(connection);

        Arglist outputArgs = new Arglist();
        outputArgs.setArg("foo", "bar");
        outputArgs.setArg(Params.ORDER_RESULT_CODE, "1");
        Exception exception = new Exception();

        doThrow(exception).when(spyConnection).checkAccessCapability(any(Arglist.class), any(SOAPLogger.class), anyInt());
        macro.setConnection(spyConnection);
        Status status = macro.create(arglist);
        assertEquals(Status.connOk, status.getConnStatus());
        assertEquals(Status.taskFailed, status.getTaskStatus());
    }
}
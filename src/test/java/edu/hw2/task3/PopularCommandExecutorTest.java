package edu.hw2.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.*;

class PopularCommandExecutorTest {

    @Test
    @DisplayName("Testing getConnection in FaultyConnectionManager")
    public void testGetConnectionFotFaultyConnection() {
        Connection connection = new FaultyConnectionManager().getConnection();
        assertThat(connection).isInstanceOf(FaultyConnection.class);
    }

    @Test
    @DisplayName("Testing getConnection in DefaultConnectionManager, expected FaultyConnections, frequency = 1")
    public void testGetConnectionForFaultyConnectionReturnFaultyConnection() {
        Connection connection = new DefaultConnectionManager(1).getConnection();
        assertThat(connection).isInstanceOf(FaultyConnection.class);
    }

    @Test
    @DisplayName("Testing tryExecute, expected no throws and StableConnection")
    public void testTryExecuteShouldNotThrowExceptionToStableConnection() {
        PopularCommandExecutor commandExecutor =
            new PopularCommandExecutor(new DefaultConnectionManager(5), 5);
        assertThatCode(commandExecutor::updatePackages).doesNotThrowAnyException();
    }

//    @Test
//    @DisplayName("Testing tryExecute, expected exception, FaultyConnection")
//    public void testTryExecuteShouldThrowExceptionToFaultyConnection() {
//        PopularCommandExecutor commandExecutor =
//            new PopularCommandExecutor(new DefaultConnectionManager(1), 2);
//        assertThatThrownBy(commandExecutor::updatePackages).isInstanceOf(ConnectionException.class);
//    }
}

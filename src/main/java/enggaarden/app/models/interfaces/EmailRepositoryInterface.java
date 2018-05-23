package enggaarden.app.models.interfaces;


public interface EmailRepositoryInterface
{
    String emailForPrimary();
    String emailForSecondary();
    String emailForExternal();
    String emailForAll();
    String emailForBoard();
}

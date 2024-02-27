package ambDAO;

import java.util.List;
import model.*;

public interface CasellaDAO {

    void guardarCasella(Casella casella);

    Casella obtenerCasella(Long id);

    List<Casella> obtenerTodasLasCaselles();

}

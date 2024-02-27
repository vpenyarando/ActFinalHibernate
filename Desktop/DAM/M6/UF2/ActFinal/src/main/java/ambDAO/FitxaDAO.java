package ambDAO;

import model.*;
import java.util.List;

public interface FitxaDAO {

    void guardarFitxa(Fitxa fitxa);

    Fitxa obtenerFitxa(Long id);

    List<Fitxa> obtenerTodasLesFitxes();

    // de momento esto
}


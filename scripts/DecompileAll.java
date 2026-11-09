// Ghidra headless: descompila todas las funciones a un archivo .c (pseudo-C)
import ghidra.app.script.GhidraScript;
import ghidra.app.decompiler.*;
import ghidra.program.model.listing.*;
import java.io.*;

public class DecompileAll extends GhidraScript {
    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        String out = args.length > 0 ? args[0] : "decompiled.c";

        DecompInterface ifc = new DecompInterface();
        ifc.setOptions(new DecompileOptions());
        ifc.openProgram(currentProgram);

        int ok = 0, fail = 0;
        try (PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(out)))) {
            w.println("// Descompilado con Ghidra: " + currentProgram.getName());
            w.println("// Pseudo-C: NO compila tal cual, sirve para leer y entender.\n");
            FunctionIterator it = currentProgram.getFunctionManager().getFunctions(true);
            while (it.hasNext() && !monitor.isCancelled()) {
                Function f = it.next();
                if (f.isThunk()) continue;
                w.println("// ---- " + f.getName(true) + " @ " + f.getEntryPoint());
                DecompileResults r = ifc.decompileFunction(f, 60, monitor);
                if (r.decompileCompleted()) {
                    w.println(r.getDecompiledFunction().getC());
                    ok++;
                } else {
                    w.println("// FALLO: " + r.getErrorMessage() + "\n");
                    fail++;
                }
            }
        }
        println("Funciones descompiladas: " + ok + ", fallidas: " + fail);
    }
}

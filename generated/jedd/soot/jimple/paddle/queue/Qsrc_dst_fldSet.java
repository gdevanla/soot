package soot.jimple.paddle.queue;

import soot.util.*;
import soot.jimple.paddle.bdddomains.*;
import soot.jimple.paddle.*;
import soot.jimple.toolkits.callgraph.*;
import soot.*;
import soot.util.queue.*;
import jedd.*;
import java.util.*;

public final class Qsrc_dst_fldSet extends Qsrc_dst_fld {
    public Qsrc_dst_fldSet(String name) { super(name); }
    
    private LinkedList readers = new LinkedList();
    
    public void add(VarNode _src, VarNode _dst, PaddleField _fld) {
        invalidate();
        Rsrc_dst_fld.Tuple in = new Rsrc_dst_fld.Tuple(_src, _dst, _fld);
        for (Iterator it = readers.iterator(); it.hasNext(); ) {
            Rsrc_dst_fldSet reader = (Rsrc_dst_fldSet) it.next();
            reader.add(in);
        }
    }
    
    public void add(final jedd.internal.RelationContainer in) { throw new RuntimeException(); }
    
    public Rsrc_dst_fld reader(String rname) {
        Rsrc_dst_fld ret = new Rsrc_dst_fldSet(name + ":" + rname);
        readers.add(ret);
        return ret;
    }
    
    public Rsrc_dst_fld revreader(String rname) {
        Rsrc_dst_fld ret = new Rsrc_dst_fldRev(name + ":" + rname);
        readers.add(ret);
        return ret;
    }
}
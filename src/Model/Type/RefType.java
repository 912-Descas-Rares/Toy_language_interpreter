package Model.Type;

import Model.Value.IVal;
import Model.Value.RefVal;

public class RefType implements IType {
     IType inner;

    public RefType(IType inner) {
        this.inner = inner;   // there may be some problems here
    }

    public IType getInner() {
        return inner;
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof RefType;
    }

    @Override
    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }

    public static IVal defaultValue(IType type) { return new RefVal(0, type); }
}


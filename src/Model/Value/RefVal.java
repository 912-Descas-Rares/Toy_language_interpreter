package Model.Value;

import Model.Type.IType;
import Model.Type.RefType;

public class RefVal implements IVal {
    int address;
    IType locationType;

    public RefVal(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddr() {
        return address;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof RefVal)
            return address == ((RefVal) another).getAddr() && locationType.equals(((RefVal) another).getType());
        else return false;
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType.toString() + ")";
    }

    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    @Override
    public  IVal defaultValue() {
        return new RefVal(0, null);
    }
}


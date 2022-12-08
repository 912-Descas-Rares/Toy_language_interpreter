package Model.Value;

import Model.Type.IType;

public interface IVal {
    IType getType();

    IVal defaultValue();
}

package team.latte.LatteIsAHorse.common.domain;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;

public class AbstractEnumAttributeConverter<E extends Enum<E> & EnumCommonType> implements AttributeConverter<E, Integer> {

    private Class<E> targetEnumClass;
    private boolean nullable;
    private String enumName;

    public AbstractEnumAttributeConverter(Class<E> targetEnumClass, boolean nullable, String enumName) {
        this.targetEnumClass = targetEnumClass;
        this.nullable = nullable;
        this.enumName = enumName;
    }

    @Override
    public Integer convertToDatabaseColumn(E attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException(String.format("%s는 NULL로 저장할 수 없습니다.", enumName));
        }
        return attribute.getCode();
    }

    @Override
    public E convertToEntityAttribute(Integer code) {
        if (!nullable && code == 0) {
            throw new IllegalArgumentException(String.format("%s가 DB에 empty(%d)로 저장 되어 있습니다.", enumName, code));
        }
        return EnumSet.allOf(targetEnumClass).stream()
                .filter(c -> c.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("enum=[%s], code=[%d]가 존재하지 않습니다.", targetEnumClass.getName(), code)));
    }
}

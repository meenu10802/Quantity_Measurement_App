package com.bridgelabz;

import com.bridgelabz.unit.LengthUnit;
import com.bridgelabz.unit.VolumeUnit;
import com.bridgelabz.unit.WeightUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testSubtraction_FeetMinusFeet() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(5.0, LengthUnit.FEET);

        assertEquals(
                new Quantity<>(5.0, LengthUnit.FEET),
                q1.subtract(q2)
        );
    }

    @Test
    void testSubtraction_CrossUnit() {

        Quantity<LengthUnit> feet =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(6.0, LengthUnit.INCH);

        assertEquals(
                new Quantity<>(9.5, LengthUnit.FEET),
                feet.subtract(inches)
        );
    }

    @Test
    void testSubtraction_Negative() {

        Quantity<LengthUnit> a =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(10.0, LengthUnit.FEET);

        assertEquals(
                new Quantity<>(-5.0, LengthUnit.FEET),
                a.subtract(b)
        );
    }

    @Test
    void testDivision_SameUnit() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(5.0, a.divide(b));
    }

    @Test
    void testDivision_CrossUnit() {

        Quantity<LengthUnit> inches =
                new Quantity<>(24.0, LengthUnit.INCH);

        Quantity<LengthUnit> feet =
                new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(1.0, inches.divide(feet));
    }

    @Test
    void testDivision_Zero() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> zero =
                new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class,
                () -> a.divide(zero));
    }

    @Test
    void testVolumeSubtraction() {

        Quantity<VolumeUnit> v1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        assertEquals(
                new Quantity<>(4.5, VolumeUnit.LITRE),
                v1.subtract(v2)
        );
    }

    @Test
    void testWeightDivision() {

        Quantity<WeightUnit> kg =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> kg2 =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertEquals(2.0, kg.divide(kg2));
    }
}
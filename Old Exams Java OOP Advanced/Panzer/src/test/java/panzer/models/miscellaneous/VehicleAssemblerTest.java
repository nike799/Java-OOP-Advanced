package panzer.models.miscellaneous;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import panzer.contracts.*;

import java.math.BigDecimal;

public class VehicleAssemblerTest {
    private VehicleAssembler vehicleAssembler;
    private AttackModifyingPart attackModifyingPart;
    private DefenseModifyingPart defenseModifyingPart;
    private HitPointsModifyingPart hitPointsModifyingPart;



    @Before
    public void setUp() throws Exception {
        this.vehicleAssembler = new VehicleAssembler();
        this.attackModifyingPart = Mockito.mock(AttackModifyingPart.class);
        this.defenseModifyingPart = Mockito.mock(DefenseModifyingPart.class);
        this.hitPointsModifyingPart = Mockito.mock(HitPointsModifyingPart.class);
        vehicleAssembler.addArsenalPart(this.attackModifyingPart);
        vehicleAssembler.addEndurancePart(this.hitPointsModifyingPart);
        vehicleAssembler.addShellPart(this.defenseModifyingPart);


    }

    @Test
    public void getTotalWeight() {
        // Arrange
        Mockito.when(this.attackModifyingPart.getWeight()).thenReturn(10.0);
        Mockito.when(this.defenseModifyingPart.getWeight()).thenReturn(20.0);
        Mockito.when(this.hitPointsModifyingPart.getWeight()).thenReturn(30.0);
        // Act
        double actualTotalWeight = this.vehicleAssembler.getTotalWeight();
        double expectedTotalWeight = 60;
        // Assert
        Assert.assertEquals(expectedTotalWeight, actualTotalWeight, 0.1);
    }

    @Test
    public void getTotalPrice() {
        // ARRANGE
        Mockito.when(this.attackModifyingPart.getPrice()).thenReturn(new BigDecimal(10));
        Mockito.when(this.defenseModifyingPart.getPrice()).thenReturn(new BigDecimal(15));
        Mockito.when(this.hitPointsModifyingPart.getPrice()).thenReturn(new BigDecimal(25));
        // ACT
        BigDecimal expectedPrice = new BigDecimal(50);
        BigDecimal actualPrice = this.vehicleAssembler.getTotalPrice();
        // ASSERT
        Assert.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void getTotalAttackModification() {
        // Arrange
        Mockito.when(this.attackModifyingPart.getAttackModifier()).thenReturn(2000000000);

        // Act
        long expectedTotalAttackModification = 2000000000L;
        long actualTotalAttackModification = this.vehicleAssembler.getTotalAttackModification();
        // Assert
        Assert.assertEquals(expectedTotalAttackModification, actualTotalAttackModification);

    }

    @Test
    public void getTotalDefenseModification() {
        // Arrange
        this.vehicleAssembler.addShellPart(this.defenseModifyingPart);
        Mockito.when(this.defenseModifyingPart.getDefenseModifier()).thenReturn(2000000000);
        // Act
        long expectedTotalAttackModification = 4000000000L;
        long actualTotalAttackModification = this.vehicleAssembler.getTotalDefenseModification();
        // Assert
        Assert.assertEquals(expectedTotalAttackModification, actualTotalAttackModification);
    }

    @Test
    public void getTotalHitPointModification() {
        // Arrange
        this.vehicleAssembler.addEndurancePart(this.hitPointsModifyingPart);
        Mockito.when(this.hitPointsModifyingPart.getHitPointsModifier()).thenReturn(2000000000);
        // Act
        long expectedTotalAttackModification = 4000000000L;
        long actualTotalAttackModification = this.vehicleAssembler.getTotalHitPointModification();
        // Assert
        Assert.assertEquals(expectedTotalAttackModification, actualTotalAttackModification);
    }

    @Test
    public void addArsenalPart() {
        // Arrange
        Assembler assembler = new VehicleAssembler();
        AttackModifyingPart part = Mockito.mock(AttackModifyingPart.class);
        AttackModifyingPart part2 = Mockito.mock(AttackModifyingPart.class);
        AttackModifyingPart part3 = Mockito.mock(AttackModifyingPart.class);
        Mockito.when(part.getAttackModifier()).thenReturn(2000000000);
        Mockito.when(part2.getAttackModifier()).thenReturn(2000000000);
        Mockito.when(part3.getAttackModifier()).thenReturn(2000000000);
        assembler.addArsenalPart(part);
        assembler.addArsenalPart(part2);
        assembler.addArsenalPart(part3);
        // Act
        long expectedSum = 6000000000L;
        long actualSum = assembler.getTotalAttackModification();
        // Assert
        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test
    public void addShellPart() {
        // Arrange
        Assembler assembler = new VehicleAssembler();
        DefenseModifyingPart part = Mockito.mock(DefenseModifyingPart.class);
        DefenseModifyingPart part2 = Mockito.mock(DefenseModifyingPart.class);
        DefenseModifyingPart part3 = Mockito.mock(DefenseModifyingPart.class);
        Mockito.when(part.getDefenseModifier()).thenReturn(2000000000);
        Mockito.when(part2.getDefenseModifier()).thenReturn(2000000000);
        Mockito.when(part3.getDefenseModifier()).thenReturn(2000000000);
        assembler.addShellPart(part);
        assembler.addShellPart(part2);
        assembler.addShellPart(part3);
        // Act
        long expectedSum = 6000000000L;
        long actualSum = assembler.getTotalDefenseModification();
        // Assert
        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test
    public void addEndurancePart() {
        // Arrange
        Assembler assembler = new VehicleAssembler();
        HitPointsModifyingPart part = Mockito.mock(HitPointsModifyingPart.class);
        HitPointsModifyingPart part2 = Mockito.mock(HitPointsModifyingPart.class);
        HitPointsModifyingPart part3 = Mockito.mock(HitPointsModifyingPart.class);
        Mockito.when(part.getHitPointsModifier()).thenReturn(2000000000);
        Mockito.when(part2.getHitPointsModifier()).thenReturn(2000000000);
        Mockito.when(part3.getHitPointsModifier()).thenReturn(2000000000);
        assembler.addEndurancePart(part);
        assembler.addEndurancePart(part2);
        assembler.addEndurancePart(part3);
        // Act
        long expectedSum = 6000000000L;
        long actualSum = assembler.getTotalHitPointModification();
        // Assert
        Assert.assertEquals(expectedSum, actualSum);
    }
}
package dev.ash.core.lib.dtos;

import dev.ash.core.lib.dtos.example.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("EnumDTO Tests")
class EnumDTOTest {

    @Test
    @DisplayName("Should create EnumDTO with valid parameters")
    void createEnumDTO() {
        EnumDTO dto = new EnumDTO(1, "TEST");
        assertEquals(1, dto.id());
        assertEquals("TEST", dto.value());
    }

    @Test
    @DisplayName("Should throw exception when id is null")
    void shouldThrowExceptionWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new EnumDTO(null, "TEST"));
    }

    @Test
    @DisplayName("Should throw exception when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new EnumDTO(1, null));
    }

    @Test
    @DisplayName("Should create EnumDTO from enum implementing EnumMappable")
    void shouldCreateFromEnum() {
        EnumDTO dto = EnumDTO.fromEnum(Status.ACTIVE);
        assertEquals(1, dto.id());
        assertEquals("ACTIVE", dto.value());
    }

    @Test
    @DisplayName("Should throw exception when enum value is null")
    void shouldThrowExceptionWhenEnumIsNull() {
        Status nullStatus = null;
        assertThrows(IllegalArgumentException.class, () -> EnumDTO.fromEnum(nullStatus));
    }
}

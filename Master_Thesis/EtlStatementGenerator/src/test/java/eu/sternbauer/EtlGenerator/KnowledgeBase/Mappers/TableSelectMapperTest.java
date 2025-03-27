package eu.sternbauer.EtlGenerator.KnowledgeBase.Mappers;

import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto.KBSelectDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto.KBSelectElementDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto.KBSelectMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.mapper.TableSelectMapper;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models.KBSelect;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models.KBSelectElement;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models.KBSelectMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TableSelectMapperTest {
    private static KBSelect refSelect;
    private static KBSelectElement refSelectElement;
    private static KBSelectMap refSelectMap;

    @BeforeAll
    public static void setUp() {
        refSelectMap = new KBSelectMap(20, "name21");
        refSelectElement = new KBSelectElement(10, "expression11", 12, "comment13", List.of(refSelectMap));
        refSelect = new KBSelect(1, "comment2", List.of(refSelectElement));
    }

    @Test
    public void testMapperSelect() {
        final KBSelectDTO dto = TableSelectMapper.INSTANCE.selectToDTO(refSelect);
        Assertions.assertEquals(refSelect.tableSelectId(), dto.tableSelectId());
        Assertions.assertEquals(refSelect.elements().size(), dto.elements().size());

        final KBSelect fromDTO = TableSelectMapper.INSTANCE.selectFromDTO(dto);
        Assertions.assertEquals(dto.tableSelectId(), fromDTO.tableSelectId());
        Assertions.assertEquals(dto.elements().size(), fromDTO.elements().size());
        Assertions.assertNull(fromDTO.comment());
    }

    @Test
    public void testMapperSelectElement() {
        final KBSelectElementDTO dto = TableSelectMapper.INSTANCE.selectElementToDTO(refSelectElement);
        Assertions.assertEquals(refSelectElement.tableSelectElementId(), dto.tableSelectElementId());
        Assertions.assertEquals(refSelectElement.expression(), dto.expression());
        Assertions.assertEquals(refSelectElement.asName(), dto.asName());
        Assertions.assertEquals(refSelectElement.mapping().size(), dto.mapping().size());

        final KBSelectElement fromDTO = TableSelectMapper.INSTANCE.selectElementFromDTO(dto);
        Assertions.assertEquals(dto.tableSelectElementId(), fromDTO.tableSelectElementId());
        Assertions.assertEquals(dto.expression(), fromDTO.expression());
        Assertions.assertEquals(dto.asName(), fromDTO.asName());
        Assertions.assertEquals(dto.mapping().size(), fromDTO.mapping().size());
        Assertions.assertNull(fromDTO.comment());
    }

    @Test
    public void testMapperSelectMap() {
        final KBSelectMapDTO dto = TableSelectMapper.INSTANCE.selectMapToDTO(refSelectMap);
        Assertions.assertEquals(refSelectMap.columnId(), dto.columnId());
        Assertions.assertEquals(refSelectMap.elementName(), dto.elementName());

        final KBSelectMap fromDTO = TableSelectMapper.INSTANCE.selectMapFromDTO(dto);
        Assertions.assertEquals(refSelectMap.columnId(), fromDTO.columnId());
        Assertions.assertEquals(refSelectMap.elementName(), fromDTO.elementName());
    }
}

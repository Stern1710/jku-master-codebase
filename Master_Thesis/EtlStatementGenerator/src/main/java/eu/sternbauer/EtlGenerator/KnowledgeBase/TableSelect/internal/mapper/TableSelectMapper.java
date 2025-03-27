package eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.mapper;

import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto.KBSelectDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto.KBSelectElementDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto.KBSelectMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models.KBSelect;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models.KBSelectElement;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models.KBSelectMap;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Maps TrableSelect data to and from data transfer objects.
 * The MapStruct library will auto-generated the actual code during build time.
 */
@Mapper
public interface TableSelectMapper {
    TableSelectMapper INSTANCE = Mappers.getMapper(TableSelectMapper.class);

    KBSelectDTO selectToDTO(KBSelect kbSelect);
    KBSelect selectFromDTO(KBSelectDTO kbSelectDTO);

    KBSelectElementDTO selectElementToDTO(KBSelectElement kbSelectElement);
    KBSelectElement selectElementFromDTO(KBSelectElementDTO kbSelectElementDTO);

    KBSelectMapDTO selectMapToDTO(KBSelectMap kbSelectMap);
    KBSelectMap selectMapFromDTO(KBSelectMapDTO kbSelectMapDTO);
}

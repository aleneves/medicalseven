package com.med.seven.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query(value = """
            select m from Medico m \
            where m.especialidade = :especialidade \
            and m.id not in(\
                select  c.medico.id from Consulta c\
                where c.data = :data\
                ) \
            order by rand() limit 1""")
    Medico findMedicoByEspecialidadeAndData(Especialidade especialidade, @NotNull @Future LocalDateTime data);
}

package id.co.telkomsigma.portalapps.repository;

import id.co.telkomsigma.portalapps.model.Pemegangkuasa;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PemegangkuasaRepository extends DataTablesRepository<Pemegangkuasa, String>, JpaRepository<Pemegangkuasa, String> {
}
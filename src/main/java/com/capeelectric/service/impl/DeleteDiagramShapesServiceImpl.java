package com.capeelectric.service.impl;

import java.util.Optional;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.DeleteDiagramShapesException;
import com.capeelectric.model.ACB;
import com.capeelectric.model.DieselGenerator;
import com.capeelectric.model.EarthElectrode;
import com.capeelectric.model.EquipotentialBonding;
import com.capeelectric.model.Fan;
import com.capeelectric.model.LTMotor;
import com.capeelectric.model.Light;
import com.capeelectric.model.MCB;
import com.capeelectric.model.MCCB;
import com.capeelectric.model.PortableAppliance;
import com.capeelectric.model.ProtectiveEarthConductors;
import com.capeelectric.model.SwitchBoard;
import com.capeelectric.model.TransformerDetails;
import com.capeelectric.repository.ACBRepository;
import com.capeelectric.repository.DieselGeneratorRepository;
import com.capeelectric.repository.EarthElectrodeRepo;
import com.capeelectric.repository.EquipBondRepository;
import com.capeelectric.repository.FanRepository;
import com.capeelectric.repository.LTMotorRepository;
import com.capeelectric.repository.LightRepository;
import com.capeelectric.repository.MCBRepository;
import com.capeelectric.repository.MCCBRepository;
import com.capeelectric.repository.PortableApplianceRepository;
import com.capeelectric.repository.SLDProtectiveEarthConductorsRepo;
import com.capeelectric.repository.SwitchBoardRepository;
import com.capeelectric.repository.TransformerRepository;
import com.capeelectric.service.DeleteDiagramShapesService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class DeleteDiagramShapesServiceImpl implements DeleteDiagramShapesService {
	private static final Logger logger = LoggerFactory.getLogger(DeleteDiagramShapesServiceImpl.class);
	@Autowired
	private ACBRepository acbRepository;
	@Autowired
	private SwitchBoardRepository switchBoardRepository;
	@Autowired
	private LightRepository lightRepository;
	@Autowired
	private MCBRepository mcbRepository;
	@Autowired
	private MCCBRepository mccbRepository;
	@Autowired
	private LTMotorRepository ltMotorRepository;
	@Autowired
	private TransformerRepository transformerRepository;	
	@Autowired
	private PortableApplianceRepository portableAppliancesRepository;
	@Autowired
	private FanRepository fanRepository;
	@Autowired
	private DieselGeneratorRepository dieselGeneratorRepository;
	@Autowired
	private EquipBondRepository equipBondRepository;
	@Autowired
	private EarthElectrodeRepo earthElectrodeRepo;
	@Autowired
	private SLDProtectiveEarthConductorsRepo protectiveEarthConductorsRepo;

	@Override
	@Modifying
	public void deleteDiagramShapes(String nodeIds) throws DeleteDiagramShapesException {
		logger.info("called deleteDeleteDiagramShapes function");
		String[] split = nodeIds.split(",");
		for (String nodeId : split) {
			if (nodeId != null && !nodeId.isEmpty()) {
				switch (demo(nodeId)) {
				case "ACB":
					Optional<ACB> acbRepo = acbRepository.findByNodeId(nodeId);
					if (acbRepo.isPresent()) {
						acbRepository.deleteById(acbRepo.get().getAcbID());
						logger.debug("acb data deleted Successfully");
					} else {
						logger.warn(nodeId + "this nodeId not avilabel for acb table");
					}
					break;
				case "Distribution board":
					Optional<SwitchBoard> switchBoardrepo = switchBoardRepository.findByNodeId(nodeId);
					if (switchBoardrepo.isPresent()) {
						switchBoardRepository.deleteById(switchBoardrepo.get().getSwitchBoardId());
						logger.debug("Distribution board data deleted Successfully");
					} else {
						logger.warn(nodeId + "this nodeId not availabel for Distribution Board Table");
					}
					break;
				case "Light":
					Optional<Light> lightrepo = lightRepository.findByNodeId(nodeId);
					if (lightrepo.isPresent()) {
						lightRepository.deleteById(lightrepo.get().getLightId());
						logger.debug("Light data deleted Successfully");
					} else {
						logger.warn(nodeId + "this nodeId not available for Light Table");
					}
					break;
				case "MCB":
					Optional<MCB> mcbrepo = mcbRepository.findByNodeId(nodeId);
					if (mcbrepo.isPresent()) {
						mcbRepository.deleteById(mcbrepo.get().getMcbID());
						logger.debug("MCB data deleted Successfully");
					} else {
						logger.warn(nodeId + "this nodeId not available for MCB Table");
					}
					break;
				case "MCB_with_RCD":
					Optional<MCB> mcbrepos = mcbRepository.findByNodeId(nodeId);
					if (mcbrepos.isPresent()) {
						mcbRepository.deleteById(mcbrepos.get().getMcbID());
						logger.debug("MCB with RCD data deleted Successfully");
					} else {
						logger.warn(nodeId + " This nodeId not available MCB with RCD data Table");
					}
					break;
				case "MCCB":
					Optional<MCCB> mccbrepo = mccbRepository.findByNodeId(nodeId);
					if (mccbrepo.isPresent()) {
						mccbRepository.deleteById(mccbrepo.get().getMccbID());
						logger.debug("MCCB data deleted Successfully");
					} else {
						logger.warn(nodeId + " this nodeId is not avilable MCCB data Table ");
					}
					break;
				case "Motor":
					Optional<LTMotor> motorrepo = ltMotorRepository.findByNodeId(nodeId);
					if (motorrepo.isPresent()) {
						ltMotorRepository.deleteById(motorrepo.get().getMotorId());
						logger.debug("Motor data deleted Successfully");
					} else {
						logger.warn(nodeId + " this nodeId is not avilable Motor data Table ");
					}
					break;
				case "Transformer_delta_delta":
					Optional<TransformerDetails> transformaerRepo = transformerRepository.findByNodeId(nodeId);
					if (transformaerRepo.isPresent()) {
						transformerRepository.deleteById(transformaerRepo.get().getTransformerDetailsId());
						logger.debug("Transformer delta delta data deleted Successfully");
					} else {
						logger.warn(nodeId + " this nodeId is not avilable Transformer data Table ");
					}

					break;
				case "Transformer_delta_star":
					Optional<SwitchBoard> switchBoard = switchBoardRepository.findByNodeId(nodeId);
					if (switchBoard.isPresent()) {
						transformerRepository.deleteById(switchBoard.get().getSwitchBoardId());
						logger.debug("Transformer delta star SWITCH_BOARD_table data deleted Successfully");
					} else {
						logger.warn(nodeId + " this nodeId is not avilable Transformer_delta_star SWITCH_BOARD_table");
					}

					break;
				case "Transformer_star_delta":
					Optional<TransformerDetails> transformerDetails = transformerRepository.findByNodeId(nodeId);
					if (transformerDetails.isPresent()) {
						transformerRepository.deleteById(transformerDetails.get().getTransformerDetailsId());
						logger.debug("Transformer star delta data deleted Successfully");
					} else {
						logger.warn(nodeId + " this nodeId is not avilable Transformer_star_delta SWITCH_BOARD_table");

					}

					break;
				case "Transformer_star_star":
					Optional<TransformerDetails> transformerDetailsRepo = transformerRepository.findByNodeId(nodeId);
					if (transformerDetailsRepo.isPresent()) {
						transformerRepository.deleteById(transformerDetailsRepo.get().getTransformerDetailsId());
						logger.debug("Transformer star star data deleted Successfully");
					} else {
						logger.warn(nodeId + " this nodeId is not avilable Transformer_star_star SWITCH_BOARD_table");

					}

					break;
				case "PortableAppliance":
					Optional<PortableAppliance> portableApplicancerepo = portableAppliancesRepository
							.findByNodeId(nodeId);
					if (portableApplicancerepo.isPresent()) {
						portableAppliancesRepository.deleteById(portableApplicancerepo.get().getPortableApplianceId());
						logger.debug("PortableAppliance data deleted Successfully");
					} else {
						logger.warn(nodeId + " this nodeId is not avilable PortableAppliance");
					}

					break;
				case "Fan":
					Optional<Fan> fan = fanRepository.findByNodeId(nodeId);
					if (fan.isPresent()) {
						fanRepository.deleteById(fan.get().getFanId());
						logger.debug("Fan data deleted Successfully");
					} else {
						logger.warn(nodeId + " this nodeId is not avilable Fan");
					}

					break;
				case "DieselGenerator":
					Optional<DieselGenerator> dieselGenerator = dieselGeneratorRepository.findByNodeId(nodeId);
					if (dieselGenerator.isPresent()) {
						dieselGeneratorRepository.deleteById(dieselGenerator.get().getDieselGeneratorID());
						logger.debug("DieselGenerator data deleted Successfully");
					} else {
						logger.warn(nodeId + " this nodeId is not avilable DieselGenerator");
					}
					break;

				case "EquipBond":
					Optional<EquipotentialBonding> equipotentialBonding = equipBondRepository.findByNodeId(nodeId);
					if (equipotentialBonding.isPresent()) {
						equipBondRepository.deleteById(equipotentialBonding.get().getEquipBondID());
						logger.debug("EquipBond data deleted Successfully");
					} else {
						logger.warn(nodeId + " this nodeId is not avilable EquipBond");
					}

					break;
				case "protectiveEarthConductor":
					Optional<ProtectiveEarthConductors> protectiveEarthConductor = protectiveEarthConductorsRepo
							.findByEarthProductiveCableID(nodeId);
					if (protectiveEarthConductor.isPresent()) {
						protectiveEarthConductorsRepo
								.deleteById(protectiveEarthConductor.get().getEarthProductiveConductorID());
						logger.debug("protectiveEarthConductor data deleted Successfully");
					} else {
						logger.warn(nodeId + " this nodeId is not avilable protectiveEarthConductor");
					}
					break;

				case "EarthElectrode":
					Optional<EarthElectrode> earthElectrode = earthElectrodeRepo.findByNodeId(nodeId);
					if (earthElectrode.isPresent()) {
						earthElectrodeRepo.deleteById(earthElectrode.get().getEarthconnectorid());
						logger.debug("EarthElectrode data deleted Successfully");
					} else {
						logger.warn(nodeId + " this nodeId is not avilable EarthElectrode");
					}
					break;

				}
			}
		}

	}

	private String demo(String nodeId) {
		if (nodeId.contains("ACB")) {
			return "ACB";
		} else if (nodeId.contains("Distribution board")) {
			return "Distribution board";

		} else if (nodeId.contains("Light")) {
			return "Light";

		} else if (nodeId.contains("MCB")) {
			return "MCB";
		} else if (nodeId.contains("MCB_with_RCD")) {
			return "MCB_with_RCD";

		} else if (nodeId.contains("MCCB")) {
			return "MCCB";

		} else if (nodeId.contains("Motor")) {
			return "Motor";

		} else if (nodeId.contains("Transformer_delta_delta")) {
			return "Transformer_delta_delta";

		} else if (nodeId.contains("Transformer_delta_star")) {
			return "Transformer_delta_star";

		} else if (nodeId.contains("Transformer_star_delta")) {
			return "Transformer_star_delta";

		} else if (nodeId.contains("Transformer_star_star")) {
			return "Transformer_star_star";

		} else if (nodeId.contains("PortableAppliance")) {
			return "PortableAppliance";

		} else if (nodeId.contains("Fan")) {
			return "Fan";

		} else if (nodeId.contains("DieselGenerator")) {
			return "DieselGenerator";

		} else if (nodeId.contains("EquipBond")) {
			return "EquipBond";

		} else if (nodeId.contains("protectiveEarthConductor")) {
			return "protectiveEarthConductor";
		} else if (nodeId.contains("EarthElectrode")) {
			return "EarthElectrode";
		} else {
			return "Wired";

		}

	}
}

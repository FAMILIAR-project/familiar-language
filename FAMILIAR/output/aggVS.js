var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 Scene: {
			 Environment: {
				 LightingType: {
						 ArtificialLight: 10000 ,
						 NaturalLight: 10001 ,
				},
				 Noise: {
					 BackgroundMovement: {
							 SandOrDust: 10002 ,
							 SeaOceanWaves: 10003 ,
							 VegetationMovement: 10004 ,
					},
					 LightingVariation: {
							 Flashes: 10005 ,
							 HeadLight: 10006 ,
							 Shadows: 10007 ,
					},
				},
				 LightingConditions: {
						 Indoors: 10008 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10009 ,
									 Night: 10010 ,
							},
						},
				},
			},
			 APrioriKnowledge: {
				 DeploymentArchitecture: {
					 Sensor: {
						 Camera: {
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10011,
									},
									 LowFrameRate: 10012 ,
									 IntermediateFrameRate: 10013 ,
							},
							 DepthOfField: 10014,
							 FieldOfView: {
									 Large: 10015 ,
									 Narrow: 10016 ,
							},
							 Resolution: 10017,
							 CameraType: {
									 BlackAndWhite: 10018 ,
									 Color: 10019 ,
									 Infrared: 10020 ,
							},
						},
						 SensorProperty: {
								 MonoSensor: 10021 ,
								 MultiSensor: 10022 ,
						},
						 SensorView: {
								 SideView: 10023 ,
								 TopView: 10024 ,
								 NormalView: 10025 ,
						},
					},
					 Server: 10026,
					 ClientDefined: 10027,
					 ProgrammerDefined: 10028,
				},
				 Scenery3DModel: 10029,
				 BehaviourLibrary: 10030,
			},
		},
		 ObjectOfInterest: {
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10031 ,
							 SelfPropelled: 10032 ,
					},
					 Immovable: 10033 ,
			},
			 Cardinality: {
					 SingleObject: 10034 ,
					 GroupOfObjects: 10035 ,
			},
			 Sort: {
					 Natural: {
						 Person: 10036,
					},
					 Manufactured: {
						 Vehicle: 10037,
					},
			},
		},
		 QualityOfService: {
				 ComputerLoad: 10038 ,
				 ResponseTime: 10039 ,
				 Quality: {
						 Sensitivity: 10040 ,
						 Precision: {
							 LessPrecision: 10041,
						},
				},
		},
		 Task: {
				 Counting: 10042 ,
				 Tracking: 10043 ,
				 BehaviourRecognition: {
					 IntrusionDetection: 10044,
				},
		},
	},
	 VSPlatform: {
		 SegmentationModule: {
			 SegmFineTune: 10045,
			 Threshold: {
					 LowThreshold: 10046 ,
					 MidThreshold: 10047 ,
					 HighThreshold: 10048 ,
			},
			 TraversalAlgorithm: {
				 GridStep: {
						 HighGridStep: 10049 ,
						 LowGridStep: 10050 ,
				},
				 TraversalParameters: {
						 WithWindow: 10051 ,
						 WithMask: 10052 ,
						 Fast: 10053 ,
				},
			},
			 KernelFunction: {
				 ColorType: {
						 GreyLevel: 10054 ,
						 SystemColor: 10055 ,
				},
				 ShadowElimination: 10056,
				 ObjectAspect: {
						 Edge: 10057 ,
						 Region: 10058 ,
				},
			},
		},
		 ImageAcquisition: 10059,
		 LongTermTracking: 10060,
		 ScenarioRecognition: 10061,
		 TrackingDataFusion: 10062,
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10063 ,
				 FastClustering: 10064 ,
				 KMeansClustering: 10065 ,
		},
		 ReferenceImageUpdating: 10066,
		 FrameToFrameTracking: 10067,
		 ClassificationModule: {
			 Model: {
					 ModelType: {
							 OmegaModel: 10068 ,
							 ThreeDModel: 10069 ,
					},
					 Ellipse: 10070 ,
					 Shape: 10071 ,
					 Parallelepiped: 10072 ,
					 GravityCenter: 10073 ,
			},
			 Contour: 10074,
			 Split: {
					 SplitStandard: 10075 ,
					 SplitGravityCenter: 10076 ,
			},
			 ClassificationStandard: {
				 Density: 10077,
			},
			 Merge: {
					 FusionGravityCenter: 10078 ,
					 FusionStandard: 10079 ,
			},
		},
	},
}

};


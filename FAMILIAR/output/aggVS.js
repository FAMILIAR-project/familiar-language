var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 ObjectOfInterest: {
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10000 ,
							 SelfPropelled: 10001 ,
					},
					 Immovable: 10002 ,
			},
			 Sort: {
					 Natural: {
						 Person: 10003,
					},
					 Manufactured: {
						 Vehicle: 10004,
					},
			},
			 Cardinality: {
					 SingleObject: 10005 ,
					 GroupOfObjects: 10006 ,
			},
		},
		 Task: {
				 Counting: 10007 ,
				 Tracking: 10008 ,
				 BehaviourRecognition: {
					 IntrusionDetection: 10009,
				},
		},
		 Scene: {
			 APrioriKnowledge: {
				 Scenery3DModel: 10010,
				 BehaviourLibrary: 10011,
				 DeploymentArchitecture: {
					 ClientDefined: 10012,
					 Server: 10013,
					 Sensor: {
						 Camera: {
							 DepthOfField: 10014,
							 Resolution: 10015,
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10016,
									},
									 LowFrameRate: 10017 ,
									 IntermediateFrameRate: 10018 ,
							},
							 CameraType: {
									 BlackAndWhite: 10019 ,
									 Color: 10020 ,
									 Infrared: 10021 ,
							},
							 FieldOfView: {
									 Large: 10022 ,
									 Narrow: 10023 ,
							},
						},
						 SensorView: {
								 SideView: 10024 ,
								 TopView: 10025 ,
								 NormalView: 10026 ,
						},
						 SensorProperty: {
								 MonoSensor: 10027 ,
								 MultiSensor: 10028 ,
						},
					},
					 ProgrammerDefined: 10029,
				},
			},
			 Environment: {
				 LightingType: {
						 ArtificialLight: 10030 ,
						 NaturalLight: 10031 ,
				},
				 Noise: {
					 LightingVariation: {
							 Flashes: 10032 ,
							 HeadLight: 10033 ,
							 Shadows: 10034 ,
					},
					 BackgroundMovement: {
							 SandOrDust: 10035 ,
							 SeaOceanWaves: 10036 ,
							 VegetationMovement: 10037 ,
					},
				},
				 LightingConditions: {
						 Indoors: 10038 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10039 ,
									 Night: 10040 ,
							},
						},
				},
			},
		},
		 QualityOfService: {
				 ComputerLoad: 10041 ,
				 ResponseTime: 10042 ,
				 Quality: {
						 Sensitivity: 10043 ,
						 Precision: {
							 LessPrecision: 10044,
						},
				},
		},
	},
	 VSPlatform: {
		 SegmentationModule: {
			 KernelFunction: {
				 ColorType: {
						 GreyLevel: 10045 ,
						 SystemColor: 10046 ,
				},
				 ShadowElimination: 10047,
				 ObjectAspect: {
						 Edge: 10048 ,
						 Region: 10049 ,
				},
			},
			 Threshold: {
					 LowThreshold: 10050 ,
					 MidThreshold: 10051 ,
					 HighThreshold: 10052 ,
			},
			 TraversalAlgorithm: {
				 TraversalParameters: {
						 WithWindow: 10053 ,
						 WithMask: 10054 ,
						 Fast: 10055 ,
				},
				 GridStep: {
						 HighGridStep: 10056 ,
						 LowGridStep: 10057 ,
				},
			},
			 SegmFineTune: 10058,
		},
		 ReferenceImageUpdating: 10059,
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10060 ,
				 FastClustering: 10061 ,
				 KMeansClustering: 10062 ,
		},
		 TrackingDataFusion: 10063,
		 ClassificationModule: {
			 Contour: 10064,
			 Split: {
					 SplitStandard: 10065 ,
					 SplitGravityCenter: 10066 ,
			},
			 Model: {
					 ModelType: {
							 OmegaModel: 10067 ,
							 ThreeDModel: 10068 ,
					},
					 Ellipse: 10069 ,
					 Shape: 10070 ,
					 Parallelepiped: 10071 ,
					 GravityCenter: 10072 ,
			},
			 Merge: {
					 FusionGravityCenter: 10073 ,
					 FusionStandard: 10074 ,
			},
			 ClassificationStandard: {
				 Density: 10075,
			},
		},
		 FrameToFrameTracking: 10076,
		 LongTermTracking: 10077,
		 ScenarioRecognition: 10078,
		 ImageAcquisition: 10079,
	},
}

};


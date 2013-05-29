var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 Task: {
				 Counting: 10000 ,
				 Tracking: 10001 ,
				 BehaviourRecognition: {
					 IntrusionDetection: 10002,
				},
		},
		 QualityOfService: {
				 ComputerLoad: 10003 ,
				 ResponseTime: 10004 ,
				 Quality: {
						 Sensitivity: 10005 ,
						 Precision: {
							 LessPrecision: 10006,
						},
				},
		},
		 ObjectOfInterest: {
			 Cardinality: {
					 SingleObject: 10007 ,
					 GroupOfObjects: 10008 ,
			},
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10009 ,
							 SelfPropelled: 10010 ,
					},
					 Immovable: 10011 ,
			},
			 Sort: {
					 Natural: {
						 Person: 10012,
					},
					 Manufactured: {
						 Vehicle: 10013,
					},
			},
		},
		 Scene: {
			 APrioriKnowledge: {
				 BehaviourLibrary: 10014,
				 Scenery3DModel: 10015,
				 DeploymentArchitecture: {
					 Server: 10016,
					 Sensor: {
						 SensorView: {
								 SideView: 10017 ,
								 TopView: 10018 ,
								 NormalView: 10019 ,
						},
						 Camera: {
							 Resolution: 10020,
							 FieldOfView: {
									 Large: 10021 ,
									 Narrow: 10022 ,
							},
							 DepthOfField: 10023,
							 CameraType: {
									 BlackAndWhite: 10024 ,
									 Color: 10025 ,
									 Infrared: 10026 ,
							},
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10027,
									},
									 LowFrameRate: 10028 ,
									 IntermediateFrameRate: 10029 ,
							},
						},
						 SensorProperty: {
								 MonoSensor: 10030 ,
								 MultiSensor: 10031 ,
						},
					},
					 ClientDefined: 10032,
					 ProgrammerDefined: 10033,
				},
			},
			 Environment: {
				 Noise: {
					 LightingVariation: {
							 Flashes: 10034 ,
							 HeadLight: 10035 ,
							 Shadows: 10036 ,
					},
					 BackgroundMovement: {
							 SandOrDust: 10037 ,
							 SeaOceanWaves: 10038 ,
							 VegetationMovement: 10039 ,
					},
				},
				 LightingType: {
						 ArtificialLight: 10040 ,
						 NaturalLight: 10041 ,
				},
				 LightingConditions: {
						 Indoors: 10042 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10043 ,
									 Night: 10044 ,
							},
						},
				},
			},
		},
	},
	 VSPlatform: {
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10045 ,
				 FastClustering: 10046 ,
				 KMeansClustering: 10047 ,
		},
		 TrackingDataFusion: 10048,
		 LongTermTracking: 10049,
		 ScenarioRecognition: 10050,
		 ClassificationModule: {
			 Merge: {
					 FusionGravityCenter: 10051 ,
					 FusionStandard: 10052 ,
			},
			 Contour: 10053,
			 ClassificationStandard: {
				 Density: 10054,
			},
			 Model: {
					 ModelType: {
							 OmegaModel: 10055 ,
							 ThreeDModel: 10056 ,
					},
					 Ellipse: 10057 ,
					 Shape: 10058 ,
					 Parallelepiped: 10059 ,
					 GravityCenter: 10060 ,
			},
			 Split: {
					 SplitStandard: 10061 ,
					 SplitGravityCenter: 10062 ,
			},
		},
		 ImageAcquisition: 10063,
		 ReferenceImageUpdating: 10064,
		 FrameToFrameTracking: 10065,
		 SegmentationModule: {
			 TraversalAlgorithm: {
				 GridStep: {
						 HighGridStep: 10066 ,
						 LowGridStep: 10067 ,
				},
				 TraversalParameters: {
						 WithWindow: 10068 ,
						 WithMask: 10069 ,
						 Fast: 10070 ,
				},
			},
			 SegmFineTune: 10071,
			 Threshold: {
					 LowThreshold: 10072 ,
					 MidThreshold: 10073 ,
					 HighThreshold: 10074 ,
			},
			 KernelFunction: {
				 ShadowElimination: 10075,
				 ColorType: {
						 GreyLevel: 10076 ,
						 SystemColor: 10077 ,
				},
				 ObjectAspect: {
						 Edge: 10078 ,
						 Region: 10079 ,
				},
			},
		},
	},
}

};


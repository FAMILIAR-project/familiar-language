var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 Scene: {
			 Environment: {
				 LightingType: {
						 ArtificialLight: 10000 ,
						 NaturalLight: 10001 ,
				},
				 LightingConditions: {
						 Indoors: 10002 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10003 ,
									 Night: 10004 ,
							},
						},
				},
				 Noise: {
					 LightingVariation: {
							 Flashes: 10005 ,
							 HeadLight: 10006 ,
							 Shadows: 10007 ,
					},
					 BackgroundMovement: {
							 SandOrDust: 10008 ,
							 SeaOceanWaves: 10009 ,
							 VegetationMovement: 10010 ,
					},
				},
			},
			 APrioriKnowledge: {
				 Scenery3DModel: 10011,
				 DeploymentArchitecture: {
					 ClientDefined: 10012,
					 ProgrammerDefined: 10013,
					 Sensor: {
						 SensorProperty: {
								 MonoSensor: 10014 ,
								 MultiSensor: 10015 ,
						},
						 Camera: {
							 CameraType: {
									 BlackAndWhite: 10016 ,
									 Color: 10017 ,
									 Infrared: 10018 ,
							},
							 Resolution: 10019,
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10020,
									},
									 LowFrameRate: 10021 ,
									 IntermediateFrameRate: 10022 ,
							},
							 FieldOfView: {
									 Large: 10023 ,
									 Narrow: 10024 ,
							},
							 DepthOfField: 10025,
						},
						 SensorView: {
								 SideView: 10026 ,
								 TopView: 10027 ,
								 NormalView: 10028 ,
						},
					},
					 Server: 10029,
				},
				 BehaviourLibrary: 10030,
			},
		},
		 QualityOfService: {
				 ComputerLoad: 10031 ,
				 ResponseTime: 10032 ,
				 Quality: {
						 Sensitivity: 10033 ,
						 Precision: {
							 LessPrecision: 10034,
						},
				},
		},
		 ObjectOfInterest: {
			 Sort: {
					 Natural: {
						 Person: 10035,
					},
					 Manufactured: {
						 Vehicle: 10036,
					},
			},
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10037 ,
							 SelfPropelled: 10038 ,
					},
					 Immovable: 10039 ,
			},
			 Cardinality: {
					 SingleObject: 10040 ,
					 GroupOfObjects: 10041 ,
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
		 ReferenceImageUpdating: 10045,
		 ClassificationModule: {
			 Split: {
					 SplitStandard: 10046 ,
					 SplitGravityCenter: 10047 ,
			},
			 Contour: 10048,
			 Merge: {
					 FusionGravityCenter: 10049 ,
					 FusionStandard: 10050 ,
			},
			 ClassificationStandard: {
				 Density: 10051,
			},
			 Model: {
					 ModelType: {
							 OmegaModel: 10052 ,
							 ThreeDModel: 10053 ,
					},
					 Ellipse: 10054 ,
					 Shape: 10055 ,
					 Parallelepiped: 10056 ,
					 GravityCenter: 10057 ,
			},
		},
		 FrameToFrameTracking: 10058,
		 SegmentationModule: {
			 KernelFunction: {
				 ColorType: {
						 GreyLevel: 10059 ,
						 SystemColor: 10060 ,
				},
				 ShadowElimination: 10061,
				 ObjectAspect: {
						 Edge: 10062 ,
						 Region: 10063 ,
				},
			},
			 Threshold: {
					 LowThreshold: 10064 ,
					 MidThreshold: 10065 ,
					 HighThreshold: 10066 ,
			},
			 TraversalAlgorithm: {
				 TraversalParameters: {
						 WithWindow: 10067 ,
						 WithMask: 10068 ,
						 Fast: 10069 ,
				},
				 GridStep: {
						 HighGridStep: 10070 ,
						 LowGridStep: 10071 ,
				},
			},
			 SegmFineTune: 10072,
		},
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10073 ,
				 FastClustering: 10074 ,
				 KMeansClustering: 10075 ,
		},
		 ImageAcquisition: 10076,
		 LongTermTracking: 10077,
		 TrackingDataFusion: 10078,
		 ScenarioRecognition: 10079,
	},
}

};


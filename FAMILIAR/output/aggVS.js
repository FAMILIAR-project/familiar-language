var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 ObjectOfInterest: {
			 Cardinality: {
					 SingleObject: 10000 ,
					 GroupOfObjects: 10001 ,
			},
			 Sort: {
					 Natural: {
						 Person: 10002,
					},
					 Manufactured: {
						 Vehicle: 10003,
					},
			},
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10004 ,
							 SelfPropelled: 10005 ,
					},
					 Immovable: 10006 ,
			},
		},
		 Scene: {
			 APrioriKnowledge: {
				 BehaviourLibrary: 10007,
				 Scenery3DModel: 10008,
				 DeploymentArchitecture: {
					 ProgrammerDefined: 10009,
					 Server: 10010,
					 Sensor: {
						 SensorView: {
								 SideView: 10011 ,
								 TopView: 10012 ,
								 NormalView: 10013 ,
						},
						 Camera: {
							 FieldOfView: {
									 Large: 10014 ,
									 Narrow: 10015 ,
							},
							 Resolution: 10016,
							 CameraType: {
									 BlackAndWhite: 10017 ,
									 Color: 10018 ,
									 Infrared: 10019 ,
							},
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10020,
									},
									 LowFrameRate: 10021 ,
									 IntermediateFrameRate: 10022 ,
							},
							 DepthOfField: 10023,
						},
						 SensorProperty: {
								 MonoSensor: 10024 ,
								 MultiSensor: 10025 ,
						},
					},
					 ClientDefined: 10026,
				},
			},
			 Environment: {
				 LightingConditions: {
						 Indoors: 10027 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10028 ,
									 Night: 10029 ,
							},
						},
				},
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
		 ImageAcquisition: 10045,
		 FrameToFrameTracking: 10046,
		 SegmentationModule: {
			 SegmFineTune: 10047,
			 KernelFunction: {
				 ShadowElimination: 10048,
				 ObjectAspect: {
						 Edge: 10049 ,
						 Region: 10050 ,
				},
				 ColorType: {
						 GreyLevel: 10051 ,
						 SystemColor: 10052 ,
				},
			},
			 TraversalAlgorithm: {
				 GridStep: {
						 HighGridStep: 10053 ,
						 LowGridStep: 10054 ,
				},
				 TraversalParameters: {
						 WithWindow: 10055 ,
						 WithMask: 10056 ,
						 Fast: 10057 ,
				},
			},
			 Threshold: {
					 LowThreshold: 10058 ,
					 MidThreshold: 10059 ,
					 HighThreshold: 10060 ,
			},
		},
		 LongTermTracking: 10061,
		 ScenarioRecognition: 10062,
		 ClassificationModule: {
			 Contour: 10063,
			 Split: {
					 SplitStandard: 10064 ,
					 SplitGravityCenter: 10065 ,
			},
			 Merge: {
					 FusionGravityCenter: 10066 ,
					 FusionStandard: 10067 ,
			},
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
			 ClassificationStandard: {
				 Density: 10074,
			},
		},
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10075 ,
				 FastClustering: 10076 ,
				 KMeansClustering: 10077 ,
		},
		 ReferenceImageUpdating: 10078,
		 TrackingDataFusion: 10079,
	},
}

};


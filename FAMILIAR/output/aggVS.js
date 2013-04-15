var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 QualityOfService: {
				 ComputerLoad: 10000 ,
				 ResponseTime: 10001 ,
				 Quality: {
						 Sensitivity: 10002 ,
						 Precision: {
							 LessPrecision: 10003,
						},
				},
		},
		 Task: {
				 Counting: 10004 ,
				 Tracking: 10005 ,
				 BehaviourRecognition: {
					 IntrusionDetection: 10006,
				},
		},
		 ObjectOfInterest: {
			 Sort: {
					 Natural: {
						 Person: 10007,
					},
					 Manufactured: {
						 Vehicle: 10008,
					},
			},
			 Cardinality: {
					 SingleObject: 10009 ,
					 GroupOfObjects: 10010 ,
			},
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10011 ,
							 SelfPropelled: 10012 ,
					},
					 Immovable: 10013 ,
			},
		},
		 Scene: {
			 Environment: {
				 LightingConditions: {
						 Indoors: 10014 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10015 ,
									 Night: 10016 ,
							},
						},
				},
				 LightingType: {
						 ArtificialLight: 10017 ,
						 NaturalLight: 10018 ,
				},
				 Noise: {
					 BackgroundMovement: {
							 SandOrDust: 10019 ,
							 SeaOceanWaves: 10020 ,
							 VegetationMovement: 10021 ,
					},
					 LightingVariation: {
							 Flashes: 10022 ,
							 HeadLight: 10023 ,
							 Shadows: 10024 ,
					},
				},
			},
			 APrioriKnowledge: {
				 BehaviourLibrary: 10025,
				 DeploymentArchitecture: {
					 Sensor: {
						 Camera: {
							 Resolution: 10026,
							 CameraType: {
									 BlackAndWhite: 10027 ,
									 Color: 10028 ,
									 Infrared: 10029 ,
							},
							 FieldOfView: {
									 Large: 10030 ,
									 Narrow: 10031 ,
							},
							 DepthOfField: 10032,
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10033,
									},
									 LowFrameRate: 10034 ,
									 IntermediateFrameRate: 10035 ,
							},
						},
						 SensorProperty: {
								 MonoSensor: 10036 ,
								 MultiSensor: 10037 ,
						},
						 SensorView: {
								 SideView: 10038 ,
								 TopView: 10039 ,
								 NormalView: 10040 ,
						},
					},
					 ClientDefined: 10041,
					 Server: 10042,
					 ProgrammerDefined: 10043,
				},
				 Scenery3DModel: 10044,
			},
		},
	},
	 VSPlatform: {
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10045 ,
				 FastClustering: 10046 ,
				 KMeansClustering: 10047 ,
		},
		 LongTermTracking: 10048,
		 ImageAcquisition: 10049,
		 ClassificationModule: {
			 Model: {
					 ModelType: {
							 OmegaModel: 10050 ,
							 ThreeDModel: 10051 ,
					},
					 Ellipse: 10052 ,
					 Shape: 10053 ,
					 Parallelepiped: 10054 ,
					 GravityCenter: 10055 ,
			},
			 Merge: {
					 FusionGravityCenter: 10056 ,
					 FusionStandard: 10057 ,
			},
			 Split: {
					 SplitStandard: 10058 ,
					 SplitGravityCenter: 10059 ,
			},
			 Contour: 10060,
			 ClassificationStandard: {
				 Density: 10061,
			},
		},
		 ScenarioRecognition: 10062,
		 ReferenceImageUpdating: 10063,
		 SegmentationModule: {
			 SegmFineTune: 10064,
			 TraversalAlgorithm: {
				 GridStep: {
						 HighGridStep: 10065 ,
						 LowGridStep: 10066 ,
				},
				 TraversalParameters: {
						 WithWindow: 10067 ,
						 WithMask: 10068 ,
						 Fast: 10069 ,
				},
			},
			 Threshold: {
					 LowThreshold: 10070 ,
					 MidThreshold: 10071 ,
					 HighThreshold: 10072 ,
			},
			 KernelFunction: {
				 ObjectAspect: {
						 Edge: 10073 ,
						 Region: 10074 ,
				},
				 ShadowElimination: 10075,
				 ColorType: {
						 GreyLevel: 10076 ,
						 SystemColor: 10077 ,
				},
			},
		},
		 FrameToFrameTracking: 10078,
		 TrackingDataFusion: 10079,
	},
}

};

